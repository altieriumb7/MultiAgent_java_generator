package com.example.app.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import com.example.app.client.model.*;

@FeignClient(name = "UserRegistrationClient", url = "$user.registration.endpoint")
public interface UserRegistrationClient {

  @RequestMapping(method = RequestMethod.POST, value = "/api/user/register")
  UserRegistrationExternalModel call(Object body);
}