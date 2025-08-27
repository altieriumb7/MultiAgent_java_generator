package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "ClaimFileClient", url = "$claim.file.endpoint")
public interface ClaimFileClient {

  @RequestMapping(method = RequestMethod.POST, value = "/api/v1/claims")
  ClaimFileExternalModel call(Object body);
}