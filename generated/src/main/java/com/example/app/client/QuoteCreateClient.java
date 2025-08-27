package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "QuoteCreateClient", url = "$quote.create.endpoint")
public interface QuoteCreateClient {

  @RequestMapping(method = RequestMethod.POST, value = "/api/v1/quotes")
  QuoteCreateExternalModel call(Object body);
}