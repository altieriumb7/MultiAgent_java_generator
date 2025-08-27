package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.CarInsuranceApiRequestForArchitectureRfaMapper;
import com.example.app.client.model.CarInsuranceApiRequestForArchitectureRfaExternalModel;
import com.example.app.dto.CarInsuranceApiRequestForArchitectureRfaResponseDTO;
public class CarInsuranceApiRequestForArchitectureRfaMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new CarInsuranceApiRequestForArchitectureRfaMapper();
    CarInsuranceApiRequestForArchitectureRfaResponseDTO out = mapper.toDto(new CarInsuranceApiRequestForArchitectureRfaExternalModel());
    assert out != null;
  }
}