package com.wcbeh.sfa.controller;

import com.wcbeh.sfa.dto.CreateCustomerTransactionDto;
import com.wcbeh.sfa.dto.CustomerTransactionDto;
import com.wcbeh.sfa.dto.PaymentMethodDto;
import com.wcbeh.sfa.dto.TransactionHistoryDto;
import com.wcbeh.sfa.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/v1")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/me/transactions")
    public ResponseEntity<List<CustomerTransactionDto>> getCurrentCustomerTransactions() {
        List<CustomerTransactionDto> customerTransactionDtos = paymentService.getCustomerTransactions((long) 1);
        return ResponseEntity.ok(customerTransactionDtos);
    }

    @GetMapping("/me/transactions-history")
    public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory() {
        List<TransactionHistoryDto> transactionHistoryDtos = paymentService.getTransactionHistory((long) 1);
        return ResponseEntity.ok(transactionHistoryDtos);
    }

    @GetMapping("/active-payment-methods")
    public ResponseEntity<List<PaymentMethodDto>> getActivePaymentMethods() {
        List<PaymentMethodDto> paymentMethods = paymentService.getActivePaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<CustomerTransactionDto> createCustomerTransaction(
            @Valid @RequestBody CreateCustomerTransactionDto createTransactionDto) {
        CustomerTransactionDto customerTransactionDto = paymentService.createCustomerTransaction(createTransactionDto);
        return new ResponseEntity<>(customerTransactionDto, HttpStatus.CREATED);
    }

    @PostMapping("/delete-transaction/{customerTransactionId}")
    public ResponseEntity<String> deleteCustomerTransaction(
            @PathVariable("customerTransactionId") Long customerTransactionId) {
        paymentService.deleteCustomerTransaction(customerTransactionId);
        return ResponseEntity.ok("CustomerTransaction deleted successfully.");
    }

}
