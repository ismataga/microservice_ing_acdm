package com.example.feint_client.client;

import com.example.feint_client.model.client.UserRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "to_do_app", url = "https:/www.google.com")
public interface UserClient {
    @PostMapping(path = "/v1/users")
    void addUser(@RequestBody UserRequest user);
}
