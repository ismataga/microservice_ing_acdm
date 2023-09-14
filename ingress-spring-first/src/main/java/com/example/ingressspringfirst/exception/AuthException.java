package com.example.ingressspringfirst.exception;

import com.example.ingressspringfirst.model.constants.ExceptionConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class AuthException extends RuntimeException{
    private final String code;
    private final Integer httpStatus;

    public AuthException(ExceptionConstants exceptionConstants, HttpStatus httpStatus) {
        super(exceptionConstants.getMessage());
        this.code = exceptionConstants.getCode();
        this.httpStatus = httpStatus.value();
    }


}
