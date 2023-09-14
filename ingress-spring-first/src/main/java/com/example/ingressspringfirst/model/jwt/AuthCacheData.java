package com.example.ingressspringfirst.model.jwt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AuthCacheData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private AccessTokenClaimsSet accessTokenClaimsSet;
    private String publicKey;
}