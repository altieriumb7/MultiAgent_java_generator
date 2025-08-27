package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.UserRegistrationService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRegistrationController {
  private final UserRegistrationService service;

  @PostMapping("/api/user/register")
  public UserRegistrationResponseDTO handle(@RequestBody UserRegistrationRequestDTO body){
    return service.handleUserRegistration(body);
  }
}