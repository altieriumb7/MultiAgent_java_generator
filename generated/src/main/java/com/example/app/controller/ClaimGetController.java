package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.ClaimGetService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClaimGetController {
  private final ClaimGetService service;

  @GetMapping("/api/v1/claims/{claimId}")
  public ClaimGetResponseDTO handle(@RequestBody ClaimGetRequestDTO body){
    return service.handleClaimGet(body);
  }
}