package com.example.app.dto;

public class ClaimFileResponseDTO {
  private String claimId;
  private String policyId;
  private String lossDate;
  private String description;
  private String status;
  public ClaimFileResponseDTO() {}
  public String getClaimid(){ return claimId; }
  public void setClaimid(String claimId){ this.claimId = claimId; }
  public String getPolicyid(){ return policyId; }
  public void setPolicyid(String policyId){ this.policyId = policyId; }
  public String getLossdate(){ return lossDate; }
  public void setLossdate(String lossDate){ this.lossDate = lossDate; }
  public String getDescription(){ return description; }
  public void setDescription(String description){ this.description = description; }
  public String getStatus(){ return status; }
  public void setStatus(String status){ this.status = status; }
}