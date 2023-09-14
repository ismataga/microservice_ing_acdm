package com.example.ingressspringfirst.queue;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum ObjectMapperSingleton  {
    INSTANCE; // Enum constant, implicitly a singleton

    private final ObjectMapper objectMapper;

    ObjectMapperSingleton() {
        objectMapper = new ObjectMapper();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    };
}






