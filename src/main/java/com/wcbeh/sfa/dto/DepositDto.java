package com.wcbeh.sfa.dto;

import com.wcbeh.sfa.constant.Frequency;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Long customerPortfolioId;

    @NotNull
    private Frequency depositPlan;

    @NotNull
    private BigDecimal depositAmount;

}
