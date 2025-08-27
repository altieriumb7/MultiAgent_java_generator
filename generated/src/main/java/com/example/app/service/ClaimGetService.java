package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.ClaimGetMapper;
import com.example.app.client.ClaimGetClient;

@Service
@RequiredArgsConstructor
public class ClaimGetService {
  private final ClaimGetClient client;
  private final ClaimGetMapper mapper;

  public ClaimGetResponseDTO handleClaimGet(ClaimGetRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}