package com.example.feint_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeintClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeintClientApplication.class, args);
    }

}
