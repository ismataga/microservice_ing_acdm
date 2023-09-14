package com.example.ingressspringfirst.model.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  ExceptionConstants {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION","Unexpected exception occurred"),

    TOKEN_EXPIRED("TOKEN_EXPIRED","Token expired"),

    USER_UNAUTHORIZED("USER_UNAUTHORIZED","User unauthorized"),

    REFRESH_TOKEN_EXPIRED("REFRESH_TOKEN_EXPIRED","Refresh token expired"),

    REFRESH_TOKEN_COUNT_EXPIRED("REFRESH_TOKEN_COUNT_EXPIRED","Refresh token count expired");

    private final String code;
    private final String message;
}
