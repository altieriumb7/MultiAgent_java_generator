package com.example.app.dto;

public class PolicyBindRequestDTO {
  private String quoteId;
  private Integer termMonths;
  public PolicyBindRequestDTO() {}
  public String getQuoteid(){ return quoteId; }
  public void setQuoteid(String quoteId){ this.quoteId = quoteId; }
  public Integer getTermmonths(){ return termMonths; }
  public void setTermmonths(Integer termMonths){ this.termMonths = termMonths; }
}