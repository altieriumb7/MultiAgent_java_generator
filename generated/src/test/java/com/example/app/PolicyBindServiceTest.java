package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.PolicyBindService;
import com.example.app.client.PolicyBindClient;
import com.example.app.mapper.PolicyBindMapper;
import com.example.app.dto.PolicyBindRequestDTO;
import com.example.app.dto.PolicyBindResponseDTO;
public class PolicyBindServiceTest {
  @Test void handle_shouldMapResponse(){
    PolicyBindClient client = mock(PolicyBindClient.class);
    PolicyBindMapper mapper = mock(PolicyBindMapper.class);
    var service = new PolicyBindService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new PolicyBindResponseDTO());
    var out = service.handlePolicyBind(new PolicyBindRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}