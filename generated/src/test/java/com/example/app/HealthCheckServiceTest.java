package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.HealthCheckService;
import com.example.app.client.HealthCheckClient;
import com.example.app.mapper.HealthCheckMapper;
import com.example.app.dto.HealthCheckRequestDTO;
import com.example.app.dto.HealthCheckResponseDTO;
public class HealthCheckServiceTest {
  @Test void handle_shouldMapResponse(){
    HealthCheckClient client = mock(HealthCheckClient.class);
    HealthCheckMapper mapper = mock(HealthCheckMapper.class);
    var service = new HealthCheckService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new HealthCheckResponseDTO());
    var out = service.handleHealthCheck(new HealthCheckRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}