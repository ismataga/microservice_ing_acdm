package com.example.ingressspringfirst.mapper;

import com.example.ingressspringfirst.entity.UserEntity;
import com.example.ingressspringfirst.model.request.UserRequest;
import com.example.ingressspringfirst.model.response.PageableUserResponse;
import com.example.ingressspringfirst.model.response.UserResponse;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class UserMapper {
    public static UserResponse mapEntityToResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .age(user.getAge())
                .username(user.getUsername())
                .build();
    }

    public static UserEntity mapRequestToEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .age(userRequest.getAge())
                .username(userRequest.getUsername())
                .build();
    }

    public static PageableUserResponse mapToPageableResponse(Page<UserEntity> userPage) {
        return PageableUserResponse.builder()
                .users(userPage.getContent().stream().map(UserMapper::mapEntityToResponse).collect(Collectors.toList()))
                .hasNextPage(userPage.hasNext())
                .lastPageNumber(userPage.getTotalPages())
                .totalElement(userPage.getTotalElements())
                .build();
    }
}

