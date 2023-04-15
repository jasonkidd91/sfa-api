package com.wcbeh.sfa.service.impl;

import com.wcbeh.sfa.constant.TransactionStatus;
import com.wcbeh.sfa.dto.CreateCustomerTransactionDto;
import com.wcbeh.sfa.dto.CustomerTransactionDto;
import com.wcbeh.sfa.dto.PaymentMethodDto;
import com.wcbeh.sfa.dto.TransactionHistoryDto;
import com.wcbeh.sfa.entity.*;
import com.wcbeh.sfa.exception.BusinessException;
import com.wcbeh.sfa.repository.*;
import com.wcbeh.sfa.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerPortfolioRepository customerPortfolioRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<PaymentMethodDto> getActivePaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findByIsActiveTrue();
        return paymentMethods.stream()
                .map(paymentMethod -> modelMapper.map(paymentMethod, PaymentMethodDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerTransactionDto> getCustomerTransactions(Long customerId) {
        // Retrieve the active customer transactions from the service
        List<CustomerTransaction> customerTransactions = customerTransactionRepository
                .findByTransactionStatusAndCustomer_CustomerId(TransactionStatus.ACTIVE.toString(), customerId);

        // Map the entities to DTOs
        return customerTransactions.stream()
                .map(transaction -> modelMapper.map(transaction, CustomerTransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerTransactionDto createCustomerTransaction(CreateCustomerTransactionDto createTransactionDto) {
        Customer customer = customerRepository.findById((long) 1)
                .orElseThrow(() -> new BusinessException("Customer not found", "CUSTOMER_NOT_FOUND"));

        CustomerPortfolio customerPortfolio = customerPortfolioRepository
                .findById(createTransactionDto.getCustomerPortfolioId())
                .orElseThrow(() -> new BusinessException("Customer portfolio not found", "CUSTOMER_PORTFOLIO_NOT_FOUND"));

        PaymentMethod paymentMethod = paymentMethodRepository.findById(createTransactionDto.getPaymentMethodId())
                .orElseThrow(() -> new BusinessException("Payment method not found", "PAYMENT_METHOD_NOT_FOUND"));

        // Convert DTO to entity
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustomer(customer);
        customerTransaction.setCustomerPortfolio(customerPortfolio);
        customerTransaction.setTransactionAmount(createTransactionDto.getTransactionAmount());
        customerTransaction.setPaymentMethod(paymentMethod);
        customerTransaction.setFrequency(createTransactionDto.getFrequency().toString());
        customerTransaction.setTransactionStartDate(createTransactionDto.getTransactionStartDate());
        customerTransaction.setTransactionNextDate(customerTransaction.getTransactionStartDate());
        customerTransaction.setTransactionEndDate(createTransactionDto.getTransactionEndDate());
        customerTransaction.setTransactionStatus(TransactionStatus.ACTIVE.toString());

        // Create the transaction
        customerTransaction = customerTransactionRepository.save(customerTransaction);

        return modelMapper.map(customerTransaction, CustomerTransactionDto.class);
    }

    @Override
    public void deleteCustomerTransaction(Long customerTransactionId) {
        customerTransactionRepository.findById(customerTransactionId)
                .ifPresent(customerTransaction -> {
                    customerTransaction.setTransactionStatus(TransactionStatus.CANCELLED.toString());
                    customerTransactionRepository.save(customerTransaction);
                });
    }

    @Override
    public List<TransactionHistoryDto> getTransactionHistory(Long customerId) {
        // Retrieve the current customer transactions from the service
        List<Transaction> transactions = transactionRepository.findByCustomer_CustomerId(customerId);

        // Map the entities to DTOs
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionHistoryDto.class))
                .collect(Collectors.toList());
    }

}
