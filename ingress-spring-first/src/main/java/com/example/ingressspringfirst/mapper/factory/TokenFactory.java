package com.example.ingressspringfirst.mapper.factory;

import static com.example.ingressspringfirst.model.constants.AuthConstant.ISSUER;


import com.example.ingressspringfirst.model.jwt.AccessTokenClaimsSet;

import com.example.ingressspringfirst.model.jwt.RefreshTokenClaimsSet;
import java.util.Date;


public interface TokenFactory {
    static AccessTokenClaimsSet buildAccessTokenClaimsSet(Long userId, Date expirationTime) {
        return AccessTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(userId)
                .createdTime(new Date())
                .expirationTime(expirationTime)
                .build();
    }

    static RefreshTokenClaimsSet buildRefreshTokenClaimsSet(Long userId,
                                                            Integer refreshTokenExpirationCount,
                                                            Date expirationTime) {
        return RefreshTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(userId)
                .expirationTime(expirationTime)
                .count(refreshTokenExpirationCount)
                .build();
    }

}
