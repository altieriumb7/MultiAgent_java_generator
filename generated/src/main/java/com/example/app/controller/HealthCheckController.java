package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.HealthCheckService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthCheckController {
  private final HealthCheckService service;

  @GetMapping("/api/v1/health")
  public HealthCheckResponseDTO handle(@RequestBody HealthCheckRequestDTO body){
    return service.handleHealthCheck(body);
  }
}