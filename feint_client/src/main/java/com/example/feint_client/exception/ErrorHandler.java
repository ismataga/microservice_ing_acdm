package com.example.feint_client.exception;

import static com.example.feint_client.exception.ExceptionMessage.UNEXPECTED_ERROR;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle (Exception exception){
        log.error("Exception", exception);
        return new ErrorResponse(UNEXPECTED_ERROR.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle (NotFoundException exception){
        log.error("NotFoundException", exception);
        return new ErrorResponse(exception.getMessage());
    }
    @ExceptionHandler(CustomFeignException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle (CustomFeignException exception){
        log.error("CustomFeignException", exception);
        return new ErrorResponse(exception.getMessage());

    }
}
