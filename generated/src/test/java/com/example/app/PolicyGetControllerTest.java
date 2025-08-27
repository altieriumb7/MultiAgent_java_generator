package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.PolicyGetController;
import com.example.app.service.PolicyGetService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class PolicyGetControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new PolicyGetController(new PolicyGetService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(get("/api/v1/policies/{policyId}")).andExpect(status().isOk());
  }
}