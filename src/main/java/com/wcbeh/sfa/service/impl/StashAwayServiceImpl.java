package com.wcbeh.sfa.service.impl;

import com.wcbeh.sfa.constant.TransactionStatus;
import com.wcbeh.sfa.constant.TransactionType;
import com.wcbeh.sfa.dto.*;
import com.wcbeh.sfa.entity.Customer;
import com.wcbeh.sfa.entity.CustomerPortfolio;
import com.wcbeh.sfa.entity.Transaction;
import com.wcbeh.sfa.repository.CustomerPortfolioRepository;
import com.wcbeh.sfa.repository.CustomerRepository;
import com.wcbeh.sfa.repository.TransactionRepository;
import com.wcbeh.sfa.service.StashAwayService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StashAwayServiceImpl implements StashAwayService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerPortfolioRepository customerPortfolioRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CustomerPortfolioOverviewDto> getAllUserPortfolios() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerPortfolioOverviewDto> customerPortfolioOverviewDtos = new ArrayList<>();

        for (Customer customer : customers) {
            List<CustomerPortfolio> customerPortfolios =
                    customerPortfolioRepository.findByCustomer_CustomerId(customer.getCustomerId());
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

            String customerName = String.join(" ", customer.getFirstName(), customer.getLastName());

            CustomerPortfolioOverviewDto customerPortfolioOverviewDTO = new CustomerPortfolioOverviewDto();
            customerPortfolioOverviewDTO.setCustomerId(customer.getCustomerId());
            customerPortfolioOverviewDTO.setCustomerName(customerName);
            customerPortfolioOverviewDTO.setCustomerPortfolios(customerPortfolioDtos);
            customerPortfolioOverviewDTO.setTotalValue(totalValue);
            customerPortfolioOverviewDTO.setTotalInvestmentReturns(totalInvestmentReturns);

            customerPortfolioOverviewDtos.add(customerPortfolioOverviewDTO);
        }

        return customerPortfolioOverviewDtos;
    }

    @Transactional
    @Override
    public void createTransaction(DepositRequestDto depositRequestDto) {
        // Iterate through the deposit array in the DTO
        for (DepositDto depositDTO : depositRequestDto.getDepositList()) {
            // Create a new Transaction entity object
            Transaction transaction = new Transaction();

            // Map the fields from the DTO to the entity
            transaction.setCustomer(Customer.builder().customerId(depositDTO.getCustomerId()).build());
            transaction.setCustomerPortfolio(
                    CustomerPortfolio.builder().customerPortfolioId(depositDTO.getCustomerPortfolioId()).build());
            transaction.setTransactionAmount(depositDTO.getDepositAmount());
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType(TransactionType.DEPOSIT.toString());
            transaction.setTransactionStatus(TransactionStatus.ACTIVE.toString());

            // Save the transaction to the database
            transactionRepository.save(transaction);
        }

        // Iterate through the floating array in the DTO
        for (FloatingDto floatingDTO : depositRequestDto.getFloatingList()) {
            // Create a new Transaction entity object
            Transaction transaction = new Transaction();

            // Map the fields from the DTO to the entity
            transaction.setCustomer(Customer.builder().customerId(floatingDTO.getCustomerId()).build());
            transaction.setCustomerPortfolio(
                    CustomerPortfolio.builder().customerPortfolioId(floatingDTO.getCustomerPortfolioId()).build());
            transaction.setTransactionAmount(floatingDTO.getDepositAmount());
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType(TransactionType.FLOATING.toString());
            transaction.setTransactionStatus(TransactionStatus.ACTIVE.toString());

            // Save the transaction to the database
            transactionRepository.save(transaction);
        }
    }

}
