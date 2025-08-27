package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.PolicyBindService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PolicyBindController {
  private final PolicyBindService service;

  @PostMapping("/api/v1/policies")
  public PolicyBindResponseDTO handle(@RequestBody PolicyBindRequestDTO body){
    return service.handlePolicyBind(body);
  }
}