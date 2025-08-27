package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.PolicyBindMapper;
import com.example.app.client.PolicyBindClient;

@Service
@RequiredArgsConstructor
public class PolicyBindService {
  private final PolicyBindClient client;
  private final PolicyBindMapper mapper;

  public PolicyBindResponseDTO handlePolicyBind(PolicyBindRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}