package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.PolicyBindMapper;
import com.example.app.client.model.PolicyBindExternalModel;
import com.example.app.dto.PolicyBindResponseDTO;
public class PolicyBindMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new PolicyBindMapper();
    PolicyBindResponseDTO out = mapper.toDto(new PolicyBindExternalModel());
    assert out != null;
  }
}