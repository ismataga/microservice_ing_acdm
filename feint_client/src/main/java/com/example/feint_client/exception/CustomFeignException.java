package com.example.feint_client.exception;

public class CustomFeignException extends RuntimeException{
    public CustomFeignException(String message, int status){
        super(message);

    }
}
