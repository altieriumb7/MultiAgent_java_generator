package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.UserRegistrationController;
import com.example.app.service.UserRegistrationService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class UserRegistrationControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new UserRegistrationController(new UserRegistrationService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(post("/api/user/register")).andExpect(status().isOk());
  }
}