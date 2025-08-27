package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "PolicyBindClient", url = "$policy.bind.endpoint")
public interface PolicyBindClient {

  @RequestMapping(method = RequestMethod.POST, value = "/api/v1/policies")
  PolicyBindExternalModel call(Object body);
}