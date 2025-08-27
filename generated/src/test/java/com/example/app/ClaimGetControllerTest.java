package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.ClaimGetController;
import com.example.app.service.ClaimGetService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class ClaimGetControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new ClaimGetController(new ClaimGetService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(get("/api/v1/claims/{claimId}")).andExpect(status().isOk());
  }
}