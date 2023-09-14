package com.example.ingressspringfirst.exception;

import com.example.ingressspringfirst.model.constants.ExceptionConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String code;
    private String message;

    public ExceptionResponse(ExceptionConstants exceptionConstants) {
        this.code = exceptionConstants.getCode();
        this.message = exceptionConstants.getMessage();
    }

    public ExceptionResponse(String message) {
    }
}
