package com.example.feint_client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    UNEXPECTED_ERROR("Unexpected error occurred"),
    CLIENT_ERROR("Error from client");
    private final String message;
}
