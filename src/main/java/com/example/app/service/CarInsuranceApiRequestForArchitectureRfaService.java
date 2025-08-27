package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.CarInsuranceApiRequestForArchitectureRfaMapper;
import com.example.app.client.CarInsuranceApiRequestForArchitectureRfaClient;

@Service
@RequiredArgsConstructor
public class CarInsuranceApiRequestForArchitectureRfaService {
  private final CarInsuranceApiRequestForArchitectureRfaClient client;
  private final CarInsuranceApiRequestForArchitectureRfaMapper mapper;

  public CarInsuranceApiRequestForArchitectureRfaResponseDTO handleCarInsuranceApiRequestForArchitectureRfa(CarInsuranceApiRequestForArchitectureRfaRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}