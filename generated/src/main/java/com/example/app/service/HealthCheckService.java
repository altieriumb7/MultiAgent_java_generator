package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.HealthCheckMapper;
import com.example.app.client.HealthCheckClient;

@Service
@RequiredArgsConstructor
public class HealthCheckService {
  private final HealthCheckClient client;
  private final HealthCheckMapper mapper;

  public HealthCheckResponseDTO handleHealthCheck(HealthCheckRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}