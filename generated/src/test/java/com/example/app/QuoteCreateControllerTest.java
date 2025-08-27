package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.QuoteCreateController;
import com.example.app.service.QuoteCreateService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class QuoteCreateControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new QuoteCreateController(new QuoteCreateService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(post("/api/v1/quotes")).andExpect(status().isOk());
  }
}