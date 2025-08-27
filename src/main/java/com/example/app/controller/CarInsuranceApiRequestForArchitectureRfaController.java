package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.CarInsuranceApiRequestForArchitectureRfaService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CarInsuranceApiRequestForArchitectureRfaController {
  private final CarInsuranceApiRequestForArchitectureRfaService service;

  @RequestMapping("")
  public CarInsuranceApiRequestForArchitectureRfaResponseDTO handle(@RequestBody CarInsuranceApiRequestForArchitectureRfaRequestDTO body){
    return service.handleCarInsuranceApiRequestForArchitectureRfa(body);
  }
}