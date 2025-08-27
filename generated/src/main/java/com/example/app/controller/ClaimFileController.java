package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.ClaimFileService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClaimFileController {
  private final ClaimFileService service;

  @PostMapping("/api/v1/claims")
  public ClaimFileResponseDTO handle(@RequestBody ClaimFileRequestDTO body){
    return service.handleClaimFile(body);
  }
}