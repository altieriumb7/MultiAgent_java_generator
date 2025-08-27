package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "HealthCheckClient", url = "$health.check.endpoint")
public interface HealthCheckClient {

  @RequestMapping(method = RequestMethod.GET, value = "/api/v1/health")
  HealthCheckExternalModel call();
}