package com.example.ingressspringfirst.queue;

import com.example.ingressspringfirst.model.request.UserRequest;
import com.example.ingressspringfirst.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserQueueListener {
    ObjectMapper objectMapper = ObjectMapperSingleton.INSTANCE.getObjectMapper();
    private final UserService userService;

    @RabbitListener(queues = {"${rabbitmq.users.queue}"})
    public void receiveMessage(String message) {
        try {
            userService.saveUser(objectMapper.readValue(message, UserRequest.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
