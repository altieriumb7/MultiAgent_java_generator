package com.example.app;
import org.junit.jupiter.api.Test;
import com.example.app.mapper.UserRegistrationMapper;
import com.example.app.client.model.UserRegistrationExternalModel;
import com.example.app.dto.UserRegistrationResponseDTO;
public class UserRegistrationMapperTest {
  @Test void toDto_shouldNotThrow(){
    var mapper = new UserRegistrationMapper();
    UserRegistrationResponseDTO out = mapper.toDto(new UserRegistrationExternalModel());
    assert out != null;
  }
}