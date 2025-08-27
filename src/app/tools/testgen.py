from __future__ import annotations
from pathlib import Path
from typing import Dict, Any
from jinja2 import Environment, BaseLoader

JAVA_TEST = Path("src/test/java"); env = Environment(loader=BaseLoader(), trim_blocks=True, lstrip_blocks=True)

CTRL_TEST_T = env.from_string("""package {{ test_pkg }};
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import {{ base_pkg }}.controller.{{ controller_cls }};
import {{ base_pkg }}.service.{{ service_cls }};
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class {{ controller_cls }}Test {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new {{ controller_cls }}(new {{ service_cls }}(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform({{ http_builder }}("{{ path }}")).andExpect(status().isOk());
  }
}
""")
SVC_TEST_T = env.from_string("""package {{ test_pkg }};
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import {{ base_pkg }}.service.{{ service_cls }};
import {{ base_pkg }}.client.{{ client_cls }};
import {{ base_pkg }}.mapper.{{ mapper_cls }};
import {{ base_pkg }}.dto.{{ req_cls }};
import {{ base_pkg }}.dto.{{ resp_cls }};
public class {{ service_cls }}Test {
  @Test void handle_shouldMapResponse(){
    {{ client_cls }} client = mock({{ client_cls }}.class);
    {{ mapper_cls }} mapper = mock({{ mapper_cls }}.class);
    var service = new {{ service_cls }}(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new {{ resp_cls }}());
    var out = service.{{ method_name }}(new {{ req_cls }}());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}
""")
MAPPER_TEST_T = env.from_string("""package {{ test_pkg }};
import org.junit.jupiter.api.Test;
import {{ base_pkg }}.mapper.{{ mapper_cls }};
import {{ base_pkg }}.client.model.{{ ext_model }};
import {{ base_pkg }}.dto.{{ resp_cls }};
public class {{ mapper_cls }}Test {
  @Test void toDto_shouldNotThrow(){
    var mapper = new {{ mapper_impl }}();
    {{ resp_cls }} out = mapper.toDto(new {{ ext_model }}());
    assert out != null;
  }
}
""")
CLIENT_TEST_T = env.from_string("""package {{ test_pkg }};
import org.junit.jupiter.api.Test;
public class {{ client_cls }}Test {
  @Test void placeholder_compileOnly(){ assert true; }
}
""")
def render_tests(spec: Dict[str, Any], style: Dict[str, Any]) -> Dict[Path, str]:
    out: Dict[Path, str] = {}
    base_pkg = (style.get('base_package') or 'com.example.app')
    test_pkg = (style.get('test_package') or base_pkg)
    pkg_path = Path(*test_pkg.split('.'))
    for ep in spec.get('endpoints', []):
        key = ep['key']; controller_cls = _cls(key,'Controller', style); service_cls = _cls(key,'Service', style); client_cls = _cls(key,'Client', style); mapper_cls = _cls(key,'Mapper', style)
        req_cls = _cls(key,'RequestDTO', style); resp_cls = _cls(key,'ResponseDTO', style); ext_model = _cls(key,'ExternalModel', style); method_name = 'handle' + _cls(key,'', style)
        http = ep.get('method','GET').upper(); http_builder = 'get' if http=='GET' else ('post' if http=='POST' else 'request')
        out[(JAVA_TEST / pkg_path / f"{controller_cls}Test.java")] = CTRL_TEST_T.render(test_pkg=test_pkg, base_pkg=base_pkg, controller_cls=controller_cls, service_cls=service_cls, http_builder=http_builder, path=ep.get('path','/'))
        out[(JAVA_TEST / pkg_path / f"{service_cls}Test.java")] = SVC_TEST_T.render(test_pkg=test_pkg, base_pkg=base_pkg, service_cls=service_cls, client_cls=client_cls, mapper_cls=mapper_cls, req_cls=req_cls, resp_cls=resp_cls, method_name=method_name)
        mapper_impl = mapper_cls
        out[(JAVA_TEST / pkg_path / f"{mapper_cls}Test.java")] = MAPPER_TEST_T.render(test_pkg=test_pkg, base_pkg=base_pkg, mapper_cls=mapper_cls, mapper_impl=mapper_impl, ext_model=ext_model, resp_cls=resp_cls)
        out[(JAVA_TEST / pkg_path / f"{client_cls}Test.java")] = CLIENT_TEST_T.render(test_pkg=test_pkg, client_cls=client_cls)
    return out
def _cls(key: str, suffix: str, style: Dict[str, Any]) -> str:
    import re; parts = [p for p in re.split(r"[^a-zA-Z0-9]+", key) if p]; core = ''.join(p.capitalize() for p in parts)
    if suffix in ['Controller','Service','Client','Mapper']: suffix = style.get(suffix.lower()+'_suffix', suffix)
    return core + suffix
