package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.PolicyGetMapper;
import com.example.app.client.model.PolicyGetExternalModel;
import com.example.app.dto.PolicyGetResponseDTO;
public class PolicyGetMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new PolicyGetMapper();
    PolicyGetResponseDTO out = mapper.toDto(new PolicyGetExternalModel());
    assert out != null;
  }
}