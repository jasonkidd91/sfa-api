package com.wcbeh.sfa.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerTransactionDto {

    private Long customerTransactionId;
    private String portfolioName;
    private Double transactionAmount;
    private String frequency;
    private Date transactionNextDate;

}
