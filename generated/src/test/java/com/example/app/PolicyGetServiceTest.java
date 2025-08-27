package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.PolicyGetService;
import com.example.app.client.PolicyGetClient;
import com.example.app.mapper.PolicyGetMapper;
import com.example.app.dto.PolicyGetRequestDTO;
import com.example.app.dto.PolicyGetResponseDTO;
public class PolicyGetServiceTest {
  @Test void handle_shouldMapResponse(){
    PolicyGetClient client = mock(PolicyGetClient.class);
    PolicyGetMapper mapper = mock(PolicyGetMapper.class);
    var service = new PolicyGetService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new PolicyGetResponseDTO());
    var out = service.handlePolicyGet(new PolicyGetRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}