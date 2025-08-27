package com.example.app.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.app.service.QuoteCreateService;
import com.example.app.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuoteCreateController {
  private final QuoteCreateService service;

  @PostMapping("/api/v1/quotes")
  public QuoteCreateResponseDTO handle(@RequestBody QuoteCreateRequestDTO body){
    return service.handleQuoteCreate(body);
  }
}