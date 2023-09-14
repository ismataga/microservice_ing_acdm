package com.example.ingressspringfirst.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserClientResponse {
    private Long id;
}
