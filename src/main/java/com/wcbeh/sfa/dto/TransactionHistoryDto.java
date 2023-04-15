package com.wcbeh.sfa.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionHistoryDto {

    private Long transactionId;
    private String portfolioName;
    private Double transactionAmount;
    private String transactionStatus;
    private String transactionType;
    private Date transactionDate;

}

