package com.wcbeh.sfa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DepositRequestDto {

    @Valid
    @Size(max = 2)
    private List<DepositDto> depositList;

    @Valid
    private List<FloatingDto> floatingList;

}
