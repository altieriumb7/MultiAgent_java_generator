package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "CarInsuranceApiRequestForArchitectureRfaClient", url = "$car.insurance.api.request.for.architecture.rfa.endpoint")
public interface CarInsuranceApiRequestForArchitectureRfaClient {

  @RequestMapping(method = RequestMethod., value = "")
  CarInsuranceApiRequestForArchitectureRfaExternalModel call();
}