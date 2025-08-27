package com.example.app.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.app.dto.*;
import com.example.app.mapper.QuoteCreateMapper;
import com.example.app.client.QuoteCreateClient;

@Service
@RequiredArgsConstructor
public class QuoteCreateService {
  private final QuoteCreateClient client;
  private final QuoteCreateMapper mapper;

  public QuoteCreateResponseDTO handleQuoteCreate(QuoteCreateRequestDTO req){
    var external = client.call(null);
    return mapper.toDto(external);
  }
}