package com.wcbeh.sfa.controller;

import com.wcbeh.sfa.dto.CustomerDto;
import com.wcbeh.sfa.dto.CustomerPortfolioDto;
import com.wcbeh.sfa.dto.CustomerPortfolioOverviewDto;
import com.wcbeh.sfa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/me")
    public ResponseEntity<CustomerDto> getCustomerInfo() {
        CustomerDto customerDTO = customerService.getCustomerInfo((long) 1);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/me/portfolio-summary")
    public ResponseEntity<CustomerPortfolioOverviewDto> getCustomerPortfolioSummary() {
        CustomerPortfolioOverviewDto customerPortfolioOverviewDto = customerService.getCustomerPortfolioSummary((long) 1);
        return ResponseEntity.ok(customerPortfolioOverviewDto);
    }

    @GetMapping("/me/active-portfolios")
    public ResponseEntity<List<CustomerPortfolioDto>> getCustomerActivePortfolios() {
        List<CustomerPortfolioDto> customerPortfolioDtos = customerService.getCustomerActivePortfolios((long) 1);
        return ResponseEntity.ok(customerPortfolioDtos);
    }

}
