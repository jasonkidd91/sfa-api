package com.wcbeh.sfa.service.impl;

import com.wcbeh.sfa.constant.TransactionType;
import com.wcbeh.sfa.dto.CustomerDto;
import com.wcbeh.sfa.dto.CustomerPortfolioDto;
import com.wcbeh.sfa.dto.CustomerPortfolioOverviewDto;
import com.wcbeh.sfa.entity.Customer;
import com.wcbeh.sfa.entity.CustomerPortfolio;
import com.wcbeh.sfa.exception.BusinessException;
import com.wcbeh.sfa.repository.CustomerPortfolioRepository;
import com.wcbeh.sfa.repository.CustomerRepository;
import com.wcbeh.sfa.repository.TransactionRepository;
import com.wcbeh.sfa.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerPortfolioRepository customerPortfolioRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public CustomerDto getCustomerInfo(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found", "CUSTOMER_NOT_FOUND"));
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerPortfolioOverviewDto getCustomerPortfolioSummary(Long customerId) {
        List<CustomerPortfolio> customerPortfolios = customerPortfolioRepository.findByCustomer_CustomerId(customerId);
        List<CustomerPortfolioDto> customerPortfolioDtos = new ArrayList<>();
        BigDecimal totalValue = BigDecimal.ZERO;
        BigDecimal totalInvestmentReturns = BigDecimal.ZERO;

        for (CustomerPortfolio customerPortfolio : customerPortfolios) {
            CustomerPortfolioDto customerPortfolioDto = modelMapper.map(customerPortfolio, CustomerPortfolioDto.class);
            BigDecimal balance = transactionRepository.sumBalanceByCustomerPortfolio(
                    customerPortfolio.getCustomerPortfolioId(),
                    customerPortfolio.getCustomer().getCustomerId());

            BigDecimal investmentReturns = transactionRepository.sumInvestmentReturnsByCustomerPortfolio(
                    customerPortfolio.getCustomerPortfolioId(),
                    customerPortfolio.getCustomer().getCustomerId(),
                    TransactionType.FLOATING.toString());

            customerPortfolioDto.setBalance(balance != null ? balance : BigDecimal.ZERO);
            customerPortfolioDto.setInvestmentReturns(investmentReturns != null ? investmentReturns : BigDecimal.ZERO);

            totalValue = totalValue.add(customerPortfolioDto.getBalance());
            totalInvestmentReturns = totalInvestmentReturns.add(customerPortfolioDto.getInvestmentReturns());

            customerPortfolioDtos.add(customerPortfolioDto);
        }

        CustomerPortfolioOverviewDto customerPortfolioOverviewDTO = new CustomerPortfolioOverviewDto();
        customerPortfolioOverviewDTO.setCustomerPortfolios(customerPortfolioDtos);
        customerPortfolioOverviewDTO.setTotalValue(totalValue);
        customerPortfolioOverviewDTO.setTotalInvestmentReturns(totalInvestmentReturns);

        return customerPortfolioOverviewDTO;
    }

    @Override
    public List<CustomerPortfolioDto> getCustomerActivePortfolios(Long customerId) {
        List<CustomerPortfolio> customerPortfolios = customerPortfolioRepository.findByCustomer_CustomerId(customerId);
        List<CustomerPortfolioDto> customerPortfolioDtos = new ArrayList<>();

        for (CustomerPortfolio customerPortfolio : customerPortfolios) {
            CustomerPortfolioDto customerPortfolioDto = modelMapper.map(customerPortfolio, CustomerPortfolioDto.class);
            customerPortfolioDtos.add(customerPortfolioDto);
        }

        return customerPortfolioDtos;
    }

}
