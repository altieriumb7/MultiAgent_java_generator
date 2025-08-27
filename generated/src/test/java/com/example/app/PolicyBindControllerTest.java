package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.PolicyBindController;
import com.example.app.service.PolicyBindService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class PolicyBindControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new PolicyBindController(new PolicyBindService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(post("/api/v1/policies")).andExpect(status().isOk());
  }
}