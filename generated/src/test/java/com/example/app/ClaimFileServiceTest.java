package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.ClaimFileService;
import com.example.app.client.ClaimFileClient;
import com.example.app.mapper.ClaimFileMapper;
import com.example.app.dto.ClaimFileRequestDTO;
import com.example.app.dto.ClaimFileResponseDTO;
public class ClaimFileServiceTest {
  @Test void handle_shouldMapResponse(){
    ClaimFileClient client = mock(ClaimFileClient.class);
    ClaimFileMapper mapper = mock(ClaimFileMapper.class);
    var service = new ClaimFileService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new ClaimFileResponseDTO());
    var out = service.handleClaimFile(new ClaimFileRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}