package com.wcbeh.sfa.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerPortfolioOverviewDto {

    List<CustomerPortfolioDto> customerPortfolios;
    BigDecimal totalValue;
    BigDecimal totalInvestmentReturns;

}
