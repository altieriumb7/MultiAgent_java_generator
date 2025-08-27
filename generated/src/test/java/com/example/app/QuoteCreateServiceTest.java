package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.QuoteCreateService;
import com.example.app.client.QuoteCreateClient;
import com.example.app.mapper.QuoteCreateMapper;
import com.example.app.dto.QuoteCreateRequestDTO;
import com.example.app.dto.QuoteCreateResponseDTO;
public class QuoteCreateServiceTest {
  @Test void handle_shouldMapResponse(){
    QuoteCreateClient client = mock(QuoteCreateClient.class);
    QuoteCreateMapper mapper = mock(QuoteCreateMapper.class);
    var service = new QuoteCreateService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new QuoteCreateResponseDTO());
    var out = service.handleQuoteCreate(new QuoteCreateRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}