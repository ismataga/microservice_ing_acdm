package com.example.feint_client.client;

import com.example.feint_client.client.decoder.CustomErrorDecoder;
import com.example.feint_client.model.client.UserRequest;  import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Profile(value = "default")
@FeignClient(name = "ingress-ms",
        url = "${client.urls.ingress-ms}",
configuration = CustomErrorDecoder.class)
public interface UserClient {
    @PostMapping(path = "/v1/users")
    void saveUser(@RequestBody UserRequest user);
}
