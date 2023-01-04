package com.olufunmi.paragonbank.dtos.requests;

import lombok.Data;

@Data
public class WithdralRequest {
    private double withdrawAmount;
    private String accountNumber;
    private String accountName;
  private String pin;

}
