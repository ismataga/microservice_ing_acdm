package com.example.ingressspringfirst.model.constants;

public interface AuthConstant {
    String RSA = "RSA";
    String AUTH_CACHE_DATA_PREFIX = "ms-auth:";
    String ISSUER = "ms-auth";
    Integer KEY_SIZE = 2048;
    Long TOKEN_EXPIRE_DAY_COUNT = 15L;
}
