package com.example.ingressspringfirst.exception;

import static com.example.ingressspringfirst.model.enums.ExceptionMessage.UNEXPECTED_ERROR;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle (Exception exception){
        log.error("Exception", exception);
        return new ExceptionResponse(UNEXPECTED_ERROR.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle (NotFoundException exception){
        log.error("NotFoundException", exception);
        return new ExceptionResponse(exception.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle (BadRequestException exception){
        log.error("BadRequestException", exception);
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> handle(AuthException ex) {
        log.error("AuthException: ", ex);
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(getExceptionResponse(ex.getCode(), ex.getMessage()));
    }
    private ExceptionResponse getExceptionResponse(String code, String message) {
        return new ExceptionResponse(code, message);
    }
}
