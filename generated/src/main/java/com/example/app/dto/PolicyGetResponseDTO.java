package com.example.app.dto;

public class PolicyGetResponseDTO {
  private String policyId;
  private String quoteId;
  private String coverage;
  private String startDate;
  private String endDate;
  private String premiumAnnual;
  private String customer;
  private String vehicle;
  private String status;
  public PolicyGetResponseDTO() {}
  public String getPolicyid(){ return policyId; }
  public void setPolicyid(String policyId){ this.policyId = policyId; }
  public String getQuoteid(){ return quoteId; }
  public void setQuoteid(String quoteId){ this.quoteId = quoteId; }
  public String getCoverage(){ return coverage; }
  public void setCoverage(String coverage){ this.coverage = coverage; }
  public String getStartdate(){ return startDate; }
  public void setStartdate(String startDate){ this.startDate = startDate; }
  public String getEnddate(){ return endDate; }
  public void setEnddate(String endDate){ this.endDate = endDate; }
  public String getPremiumannual(){ return premiumAnnual; }
  public void setPremiumannual(String premiumAnnual){ this.premiumAnnual = premiumAnnual; }
  public String getCustomer(){ return customer; }
  public void setCustomer(String customer){ this.customer = customer; }
  public String getVehicle(){ return vehicle; }
  public void setVehicle(String vehicle){ this.vehicle = vehicle; }
  public String getStatus(){ return status; }
  public void setStatus(String status){ this.status = status; }
}