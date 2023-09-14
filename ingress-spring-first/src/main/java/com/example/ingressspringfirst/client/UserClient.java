package com.example.ingressspringfirst.client;

import com.example.ingressspringfirst.model.client.UserClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-user", url = "http://localhost:8080")
public interface UserClient {
    @GetMapping("internal/v1/users")
    UserClientResponse getUser(@RequestParam String username);
}
