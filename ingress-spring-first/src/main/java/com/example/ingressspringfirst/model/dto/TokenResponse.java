package com.example.ingressspringfirst.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
