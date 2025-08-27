package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.UserRegistrationMapper;
import com.example.app.client.UserRegistrationClient;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
  private final UserRegistrationClient client;
  private final UserRegistrationMapper mapper;

  public UserRegistrationResponseDTO handleUserRegistration(UserRegistrationRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}