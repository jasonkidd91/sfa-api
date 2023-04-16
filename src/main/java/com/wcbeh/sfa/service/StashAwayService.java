package com.wcbeh.sfa.service;

import com.wcbeh.sfa.dto.CustomerPortfolioOverviewDto;
import com.wcbeh.sfa.dto.DepositRequestDto;

import java.util.List;

public interface StashAwayService {

    List<CustomerPortfolioOverviewDto> getAllUserPortfolios();

    void createTransaction(DepositRequestDto depositRequestDto);

}
