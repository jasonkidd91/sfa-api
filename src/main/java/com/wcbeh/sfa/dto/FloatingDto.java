package com.wcbeh.sfa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FloatingDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Long customerPortfolioId;

    @NotNull
    private BigDecimal depositAmount;

}
