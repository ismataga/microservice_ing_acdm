package com.example.ingressspringfirst.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    UNEXPECTED_ERROR("Unexpected error occurred"),
    USER_NOT_FOUND("User not found");
    private final String message;
}
