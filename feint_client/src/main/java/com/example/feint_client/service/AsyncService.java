package com.example.feint_client.service;

import static com.example.feint_client.mapper.UserMapper.buildUserRequest;

import com.example.feint_client.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class AsyncService {
    private final UserClient userClient;
    @Async
    public void saveUser() {
        userClient.saveUser(buildUserRequest());
    }
}
