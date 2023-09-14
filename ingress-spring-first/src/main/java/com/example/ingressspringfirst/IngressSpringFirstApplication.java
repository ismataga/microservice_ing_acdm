package com.example.ingressspringfirst;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class IngressSpringFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngressSpringFirstApplication.class, args);
    }

}
