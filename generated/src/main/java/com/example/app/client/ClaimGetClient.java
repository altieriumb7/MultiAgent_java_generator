package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "ClaimGetClient", url = "$claim.get.endpoint")
public interface ClaimGetClient {

  @RequestMapping(method = RequestMethod.GET, value = "/api/v1/claims/{claimId}")
  ClaimGetExternalModel call();
}