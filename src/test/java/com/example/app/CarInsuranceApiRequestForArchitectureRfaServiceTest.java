package com.example.app;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.example.app.service.CarInsuranceApiRequestForArchitectureRfaService;
import com.example.app.client.CarInsuranceApiRequestForArchitectureRfaClient;
import com.example.app.mapper.CarInsuranceApiRequestForArchitectureRfaMapper;
import com.example.app.dto.CarInsuranceApiRequestForArchitectureRfaRequestDTO;
import com.example.app.dto.CarInsuranceApiRequestForArchitectureRfaResponseDTO;
public class CarInsuranceApiRequestForArchitectureRfaServiceTest {
  @Test void handle_shouldMapResponse(){
    CarInsuranceApiRequestForArchitectureRfaClient client = mock(CarInsuranceApiRequestForArchitectureRfaClient.class);
    CarInsuranceApiRequestForArchitectureRfaMapper mapper = mock(CarInsuranceApiRequestForArchitectureRfaMapper.class);
    var service = new CarInsuranceApiRequestForArchitectureRfaService(client, mapper);
    when(client.call(null)).thenReturn(null);
    when(mapper.toDto(any())).thenReturn(new CarInsuranceApiRequestForArchitectureRfaResponseDTO());
    var out = service.handleCarInsuranceApiRequestForArchitectureRfa(new CarInsuranceApiRequestForArchitectureRfaRequestDTO());
    assert out != null;
    verify(client, times(1)).call(null);
    verify(mapper, times(1)).toDto(any());
  }
}