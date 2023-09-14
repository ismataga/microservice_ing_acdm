package com.example.feint_client.client.decoder;


import static com.example.feint_client.client.decoder.JsonNodeFieldName.CODE;
import static com.example.feint_client.exception.ExceptionMessage.CLIENT_ERROR;
import com.example.feint_client.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static com.example.feint_client.util.MapperUtil.MAPPER_UTIL;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        var errorMessage = CLIENT_ERROR.getMessage();
        var status = response.status();

        JsonNode jsonNode;
        try (var body = response.body().asInputStream()) {
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch (Exception e) {
            throw new CustomFeignException(CLIENT_ERROR.getMessage(), status);
        }

        if(jsonNode.has(CODE.getValue())) errorMessage = jsonNode.get(CODE.getValue()).asText();
        return new CustomFeignException(errorMessage,status);
    }
}
