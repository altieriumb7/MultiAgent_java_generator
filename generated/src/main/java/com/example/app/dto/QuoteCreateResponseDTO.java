package com.example.app.dto;

public class QuoteCreateResponseDTO {
  private String quoteId;
  private String premiumAnnual;
  private String coverage;
  private String startDate;
  private String createdAt;
  public QuoteCreateResponseDTO() {}
  public String getQuoteid(){ return quoteId; }
  public void setQuoteid(String quoteId){ this.quoteId = quoteId; }
  public String getPremiumannual(){ return premiumAnnual; }
  public void setPremiumannual(String premiumAnnual){ this.premiumAnnual = premiumAnnual; }
  public String getCoverage(){ return coverage; }
  public void setCoverage(String coverage){ this.coverage = coverage; }
  public String getStartdate(){ return startDate; }
  public void setStartdate(String startDate){ this.startDate = startDate; }
  public String getCreatedat(){ return createdAt; }
  public void setCreatedat(String createdAt){ this.createdAt = createdAt; }
}