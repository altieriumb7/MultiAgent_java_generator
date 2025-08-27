package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.QuoteCreateMapper;
import com.example.app.client.model.QuoteCreateExternalModel;
import com.example.app.dto.QuoteCreateResponseDTO;
public class QuoteCreateMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new QuoteCreateMapper();
    QuoteCreateResponseDTO out = mapper.toDto(new QuoteCreateExternalModel());
    assert out != null;
  }
}