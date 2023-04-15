package com.wcbeh.sfa.service;

import com.wcbeh.sfa.dto.CreateCustomerTransactionDto;
import com.wcbeh.sfa.dto.CustomerTransactionDto;
import com.wcbeh.sfa.dto.PaymentMethodDto;
import com.wcbeh.sfa.dto.TransactionHistoryDto;

import java.util.List;

public interface PaymentService {

    List<PaymentMethodDto> getActivePaymentMethods();

    List<CustomerTransactionDto> getCustomerTransactions(Long customerId);

    CustomerTransactionDto createCustomerTransaction(CreateCustomerTransactionDto createTransactionDto);

    void deleteCustomerTransaction(Long customerTransactionId);

    List<TransactionHistoryDto> getTransactionHistory(Long customerId);

}
