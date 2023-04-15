package com.wcbeh.sfa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;
    private String errorCode;

    // Constructors, getters, and setters
}
