from __future__ import annotations
from pathlib import Path
from typing import Dict, Any, List
import xml.etree.ElementTree as ET
import re

def mine_project_style(project_root: Path) -> Dict[str, Any]:
    style: Dict[str, Any] = {
        "base_package": "com.example.app",
        "use_mapstruct": False,
        "use_feign": True,
        "use_webflux": False,
        "junit": "junit5",
        "use_mockmvc": True,
        "controller_suffix": "Controller",
        "service_suffix": "Service",
        "mapper_suffix": "Mapper",
        "client_suffix": "Client",
        "properties_prefix": "",
        "java_version": 11,
        "spring_boot_major": None,
        "use_jakarta": False,
        "use_lombok": False,
        "use_records": False,
        "test_package": None,
    }
    if not project_root or not project_root.exists():
        return style
    pom = project_root / "pom.xml"
    if pom.exists():
        try:
            tree = ET.parse(pom); root = tree.getroot()
            ns = {'m': root.tag.split('}')[0].strip('{')} if '}' in root.tag else {}
            def tfind(node, path): return node.find(path, ns) if ns else node.find(path.replace('m:', ''))
            props = tfind(root, ".//m:properties") if ns else tfind(root, ".//properties")
            def _text(node, tag):
                if node is None: return None
                el = tfind(node, f"m:{tag}") if ns else node.find(tag)
                return el.text.strip() if (el is not None and el.text) else None
            def _to_int(v):
                try: return int(v)
                except:
                    if isinstance(v,str) and v.startswith("1."):
                        try: return int(v.split(".")[1])
                        except: return None
                    return None
            candidates = list(filter(None, [_to_int(_text(props,"maven.compiler.release")),
                                            _to_int(_text(props,"maven.compiler.source")),
                                            _to_int(_text(props,"maven.compiler.target"))]))
            if candidates: style["java_version"] = max(candidates)
            deps = root.findall('.//m:dependency', ns) if ns else root.findall('.//dependency')
            for d in deps:
                gid = (tfind(d,'m:groupId').text if tfind(d,'m:groupId') is not None else '') if ns else ((d.find('groupId').text) if d.find('groupId') is not None else '')
                aid = (tfind(d,'m:artifactId').text if tfind(d,'m:artifactId') is not None else '') if ns else ((d.find('artifactId').text) if d.find('artifactId') is not None else '')
                ga = f"{gid}:{aid}"
                if 'org.mapstruct' in ga: style["use_mapstruct"] = True
                if 'org.projectlombok' in ga or aid == 'lombok': style["use_lombok"] = True
                if 'spring-cloud-starter-openfeign' in aid: style["use_feign"] = True
                if 'spring-boot-starter-webflux' in aid: style["use_webflux"] = True
            parent = tfind(root, ".//m:parent") if ns else tfind(root, ".//parent")
            if parent is not None:
                pgid = _text(parent, "groupId"); pver = _text(parent, "version")
                if (pgid or "").startswith("org.springframework.boot"):
                    try:
                        major = int((pver or "0").split(".")[0])
                        style["spring_boot_major"] = major; style["use_jakarta"] = major >= 3
                        if major >= 3 and style["java_version"] < 17: style["java_version"] = 17
                    except: pass
        except Exception: pass
    java_src = project_root / "src" / "main" / "java"
    test_src = project_root / "src" / "test" / "java"
    packages: List[str] = []; saw_record = False
    for base in [java_src, test_src]:
        if base.exists():
            for p in base.rglob("*.java"):
                try: txt = p.read_text(encoding='utf-8', errors='ignore')
                except Exception: continue
                m = re.search(r"^\s*package\s+([a-zA-Z0-9_.]+);", txt, re.MULTILINE)
                if m: packages.append(m.group(1))
                if 'MockMvc' in txt: style['use_mockmvc'] = True
                if re.search(r"\brecord\s+\w+\(", txt): saw_record = True
    if packages: style['base_package'] = _common_package_prefix(packages) or style['base_package']
    style['use_records'] = bool(saw_record and style['java_version'] >= 17)
    for props in [project_root / "src" / "main" / "resources" / "application.properties",
                  project_root / "src" / "main" / "resources" / "application.yaml"]:
        if props.exists():
            try:
                txt = props.read_text(encoding='utf-8', errors='ignore')
                m = re.search(r"^([a-zA-Z0-9_-]+)\.[a-zA-Z0-9_-]+=", txt, re.MULTILINE)
                if m: style['properties_prefix'] = m.group(1)
            except Exception: pass
    if test_src.exists():
        base_pkg_path = Path(*style['base_package'].split('.')); candidate = test_src / base_pkg_path
        if candidate.exists(): style['test_package'] = style['base_package']
    return style

def _common_package_prefix(pkgs: List[str]) -> str:
    parts = [p.split('.') for p in pkgs]
    if not parts: return ''
    prefix = []
    for i in range(min(len(p) for p in parts)):
        col = {p[i] for p in parts}
        if len(col) == 1: prefix.append(next(iter(col)))
        else: break
    return '.'.join(prefix) if prefix else ''
