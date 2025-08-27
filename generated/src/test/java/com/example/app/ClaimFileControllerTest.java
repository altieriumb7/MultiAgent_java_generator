package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.ClaimFileController;
import com.example.app.service.ClaimFileService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class ClaimFileControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new ClaimFileController(new ClaimFileService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(post("/api/v1/claims")).andExpect(status().isOk());
  }
}