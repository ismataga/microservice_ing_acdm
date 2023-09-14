package com.example.feint_client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public enum ObjectMapperFactory {
    OBJECT_MAPPER_FACTORY;

    public ObjectMapper createObjectMapperInstance(){
            var objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper;
    }
}
