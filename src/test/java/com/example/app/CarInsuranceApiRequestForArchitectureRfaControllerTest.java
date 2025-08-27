package com.example.app;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.app.controller.CarInsuranceApiRequestForArchitectureRfaController;
import com.example.app.service.CarInsuranceApiRequestForArchitectureRfaService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class CarInsuranceApiRequestForArchitectureRfaControllerTest {
  @Test void handle_shouldReturn200() throws Exception {
    var controller = new CarInsuranceApiRequestForArchitectureRfaController(new CarInsuranceApiRequestForArchitectureRfaService(null, null));
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
    mvc.perform(request("")).andExpect(status().isOk());
  }
}