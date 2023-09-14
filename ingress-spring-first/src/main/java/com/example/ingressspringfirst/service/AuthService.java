package com.example.ingressspringfirst.service;

import com.example.ingressspringfirst.client.UserClient;
import com.example.ingressspringfirst.model.client.UserClientResponse;
import com.example.ingressspringfirst.model.dto.TokenResponse;
import com.example.ingressspringfirst.model.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${jwt.refreshToken.expiration.count}")
    private Integer refreshTokenExpirationTime;

    private final TokenService tokenService;
    @Autowired
    private final UserClient userClient;
    long count = 1L;

    public TokenResponse login(LoginRequest request) {
        var user = userClient.getUserName(request.getEmail());
        var user = UserClientResponse.of(count++);
        return tokenService.generateToken(user.getId(), refreshTokenExpirationTime);
    }
}
