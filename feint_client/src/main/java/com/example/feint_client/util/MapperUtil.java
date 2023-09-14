package com.example.feint_client.util;

import static com.example.feint_client.mapper.ObjectMapperFactory.OBJECT_MAPPER_FACTORY;

import java.io.InputStream;


public enum MapperUtil {

    MAPPER_UTIL;


    public <T> T map(InputStream source, Class<T> tClass){
        try{
            return OBJECT_MAPPER_FACTORY.createObjectMapperInstance().readValue(source,tClass);
        }catch (Exception exception){
            throw new IllegalStateException(exception.getMessage());
        }
    }
}
