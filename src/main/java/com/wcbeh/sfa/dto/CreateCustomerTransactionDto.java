package com.wcbeh.sfa.dto;

import com.wcbeh.sfa.constant.Frequency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateCustomerTransactionDto {

    @NotNull
    @DecimalMin(value = "0.01", message = "Transaction amount must be greater than 0")
    private BigDecimal transactionAmount;

    @NotNull
    private Long paymentMethodId;

    @NotNull
    private Frequency frequency;

    @NotNull
    @FutureOrPresent(message = "Transaction start date must be in the present or future")
    private Date transactionStartDate;

    @FutureOrPresent(message = "Transaction end date must be in the present or future")
    private Date transactionEndDate;

    @NotNull
    private Long customerPortfolioId;

}

