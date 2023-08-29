package com.example.feint_client.mapper;

import com.example.feint_client.model.client.UserRequest;

public class UserMapper {
    public static UserRequest buildUserRequest() {
        return UserRequest.builder()
                .username("test-user")
                .age(18)
                .build();
    }
}
