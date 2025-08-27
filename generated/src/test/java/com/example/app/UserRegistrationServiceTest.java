package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.UserRegistrationService;
import com.example.app.client.UserRegistrationClient;
import com.example.app.mapper.UserRegistrationMapper;
import com.example.app.dto.UserRegistrationRequestDTO;
import com.example.app.dto.UserRegistrationResponseDTO;
public class UserRegistrationServiceTest {
  @Test void handle_shouldMapResponse(){
    UserRegistrationClient client = mock(UserRegistrationClient.class);
    UserRegistrationMapper mapper = mock(UserRegistrationMapper.class);
    var service = new UserRegistrationService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new UserRegistrationResponseDTO());
    var out = service.handleUserRegistration(new UserRegistrationRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}