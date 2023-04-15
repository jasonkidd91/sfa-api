package com.wcbeh.sfa.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerPortfolioDto {

    private Long customerPortfolioId;
    private String portfolioName;
    private String portfolioDesc;
    private BigDecimal balance;
    private BigDecimal investmentReturns;

}
