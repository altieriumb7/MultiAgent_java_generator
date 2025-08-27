package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.ClaimFileMapper;
import com.example.app.client.model.ClaimFileExternalModel;
import com.example.app.dto.ClaimFileResponseDTO;
public class ClaimFileMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new ClaimFileMapper();
    ClaimFileResponseDTO out = mapper.toDto(new ClaimFileExternalModel());
    assert out != null;
  }
}