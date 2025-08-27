package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.ClaimGetMapper;
import com.example.app.client.model.ClaimGetExternalModel;
import com.example.app.dto.ClaimGetResponseDTO;
public class ClaimGetMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new ClaimGetMapper();
    ClaimGetResponseDTO out = mapper.toDto(new ClaimGetExternalModel());
    assert out != null;
  }
}