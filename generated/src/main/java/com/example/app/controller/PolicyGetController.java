package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.PolicyGetService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PolicyGetController {
  private final PolicyGetService service;

  @GetMapping("/api/v1/policies/{policyId}")
  public PolicyGetResponseDTO handle(@RequestBody PolicyGetRequestDTO body){
    return service.handlePolicyGet(body);
  }
}