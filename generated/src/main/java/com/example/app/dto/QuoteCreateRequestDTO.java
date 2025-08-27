package com.example.app.dto;

public class QuoteCreateRequestDTO {
  private String customer;
  private String vehicle;
  private String coverage;
  private Integer accidentFreeYears;
  private String desiredStartDate;
  public QuoteCreateRequestDTO() {}
  public String getCustomer(){ return customer; }
  public void setCustomer(String customer){ this.customer = customer; }
  public String getVehicle(){ return vehicle; }
  public void setVehicle(String vehicle){ this.vehicle = vehicle; }
  public String getCoverage(){ return coverage; }
  public void setCoverage(String coverage){ this.coverage = coverage; }
  public Integer getAccidentfreeyears(){ return accidentFreeYears; }
  public void setAccidentfreeyears(Integer accidentFreeYears){ this.accidentFreeYears = accidentFreeYears; }
  public String getDesiredstartdate(){ return desiredStartDate; }
  public void setDesiredstartdate(String desiredStartDate){ this.desiredStartDate = desiredStartDate; }
}