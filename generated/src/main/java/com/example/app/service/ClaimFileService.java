package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.ClaimFileMapper;
import com.example.app.client.ClaimFileClient;

@Service
@RequiredArgsConstructor
public class ClaimFileService {
  private final ClaimFileClient client;
  private final ClaimFileMapper mapper;

  public ClaimFileResponseDTO handleClaimFile(ClaimFileRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}