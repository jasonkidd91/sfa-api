package com.wcbeh.sfa.service;

import com.wcbeh.sfa.dto.*;

import java.util.List;

public interface CustomerService {

    CustomerDto getCustomerInfo(Long customerId);

    CustomerPortfolioOverviewDto getCustomerPortfolioSummary(Long customerId);

    List<CustomerPortfolioDto> getCustomerActivePortfolios(Long customerId);

}
