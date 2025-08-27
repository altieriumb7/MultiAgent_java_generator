package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.ClaimGetService;
import com.example.app.client.ClaimGetClient;
import com.example.app.mapper.ClaimGetMapper;
import com.example.app.dto.ClaimGetRequestDTO;
import com.example.app.dto.ClaimGetResponseDTO;
public class ClaimGetServiceTest {
  @Test void handle_shouldMapResponse(){
    ClaimGetClient client = mock(ClaimGetClient.class);
    ClaimGetMapper mapper = mock(ClaimGetMapper.class);
    var service = new ClaimGetService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new ClaimGetResponseDTO());
    var out = service.handleClaimGet(new ClaimGetRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}