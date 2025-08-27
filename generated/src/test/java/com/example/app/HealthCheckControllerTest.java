package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.HealthCheckController;
import com.example.app.service.HealthCheckService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class HealthCheckControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new HealthCheckController(new HealthCheckService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(get("/api/v1/health")).andExpect(status().isOk());
  }
}