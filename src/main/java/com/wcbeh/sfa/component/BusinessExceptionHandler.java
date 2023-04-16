package com.wcbeh.sfa.component;

import com.wcbeh.sfa.dto.ApiError;
import com.wcbeh.sfa.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(BusinessException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getErrorCode(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}
