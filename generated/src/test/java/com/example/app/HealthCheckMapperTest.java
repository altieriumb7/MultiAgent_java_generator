package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.HealthCheckMapper;
import com.example.app.client.model.HealthCheckExternalModel;
import com.example.app.dto.HealthCheckResponseDTO;
public class HealthCheckMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new HealthCheckMapper();
    HealthCheckResponseDTO out = mapper.toDto(new HealthCheckExternalModel());
    assert out != null;
  }
}