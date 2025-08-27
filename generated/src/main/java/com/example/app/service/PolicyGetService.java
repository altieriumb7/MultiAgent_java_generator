package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.PolicyGetMapper;
import com.example.app.client.PolicyGetClient;

@Service
@RequiredArgsConstructor
public class PolicyGetService {
  private final PolicyGetClient client;
  private final PolicyGetMapper mapper;

  public PolicyGetResponseDTO handlePolicyGet(PolicyGetRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}