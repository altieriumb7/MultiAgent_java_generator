package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "PolicyGetClient", url = "$policy.get.endpoint")
public interface PolicyGetClient {

  @RequestMapping(method = RequestMethod.GET, value = "/api/v1/policies/{policyId}")
  PolicyGetExternalModel call();
}