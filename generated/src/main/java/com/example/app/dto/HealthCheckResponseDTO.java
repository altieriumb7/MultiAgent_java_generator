package com.example.app.dto;

public class HealthCheckResponseDTO {
  private String status;
  public HealthCheckResponseDTO() {}
  public String getStatus(){ return status; }
  public void setStatus(String status){ this.status = status; }
}