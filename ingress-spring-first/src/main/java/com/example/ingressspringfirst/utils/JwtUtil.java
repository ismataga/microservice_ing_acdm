package com.example.ingressspringfirst.utils;

import com.example.ingressspringfirst.exception.AuthException;
import com.example.ingressspringfirst.model.jwt.AccessTokenClaimsSet;
import com.example.ingressspringfirst.model.jwt.RefreshTokenClaimsSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.security.interfaces.RSAPrivateKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;



import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import static com.example.ingressspringfirst.model.constants.AuthConstant.RSA;
import static com.example.ingressspringfirst.model.constants.AuthConstant.KEY_SIZE;
import static com.example.ingressspringfirst.model.constants.ExceptionConstants.USER_UNAUTHORIZED;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final ObjectMapper objectMapper;

    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(KEY_SIZE);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            log.error("ActionLog.generateKeyPair.error no such algorithm", e);
            throw new AuthException(USER_UNAUTHORIZED, UNAUTHORIZED);
        }
    }

    public <T> String generateToken(T tokenClaimSet, PrivateKey privateKey) {

        SignedJWT signedJWT;
        try {
            signedJWT = generateSignedJWT(objectMapper.writeValueAsString(tokenClaimSet), privateKey);
        } catch (Exception e) {
            log.error("ActionLog.generateToken.error cannot generate token", e);
            throw new AuthException(USER_UNAUTHORIZED, UNAUTHORIZED);
        }
        return signedJWT.serialize();
    }

    public void verifyToken(String token, RSAPublicKey publicKey) {
        try {
            var signedJwt = SignedJWT.parse(token);
            var verifier = new RSASSAVerifier(publicKey);

            if (!signedJwt.verify(verifier)) {
                log.error("ActionLog.verifyToken.error can't verify signedJwt");
                throw new AuthException(USER_UNAUTHORIZED, UNAUTHORIZED);
            }
        } catch (ParseException | JOSEException e) {
            log.error("ActionLog.verifyToken.error can't parse token ", e);
            throw new AuthException(USER_UNAUTHORIZED, UNAUTHORIZED);
        }
    }

    public AccessTokenClaimsSet getClaimsFromAccessToken(String accessToken) {
        AccessTokenClaimsSet claimsSet;
        try {
            claimsSet = objectMapper.readValue(getClaimsFromToken(accessToken).toString(), AccessTokenClaimsSet.class);
        } catch (IOException | ParseException e) {
            log.error("ActionLog.getClaimsFromAccessToken.error can't parse access token", e);
            throw new AuthException(USER_UNAUTHORIZED, UNAUTHORIZED);
        }
        return claimsSet;
    }

    public RefreshTokenClaimsSet getClaimsFromRefreshToken(String refreshToken) {

        RefreshTokenClaimsSet claimsSet;
        try {
            var claimsAsText = getClaimsFromToken(refreshToken).toString();
            log.info(claimsAsText);
            claimsSet = objectMapper.readValue(claimsAsText, RefreshTokenClaimsSet.class);
        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
            log.error("ActionLog.getClaimsFromRefreshToken.error can't parse refresh token", e);
            throw new AuthException(USER_UNAUTHORIZED, UNAUTHORIZED);
        }
        return claimsSet;
    }


    private JWTClaimsSet getClaimsFromToken(String token) throws ParseException {
        return (JWTClaimsSet) SignedJWT.parse(token).getJWTClaimsSet();
    }

    public String encodePublicKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    public RSAPublicKey decodePublicKey(String publicKey) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(RSA)
                    .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("ActionLog.getClaimsFromAccessToken.error can't parse access token", e);
            throw new AuthException(USER_UNAUTHORIZED, UNAUTHORIZED);
        }
    }

    public Date generateSessionExpirationTime(Integer expirationMinutes) {
        return toDate(LocalDateTime.now().plusMinutes(expirationMinutes));
    }

    public boolean isRefreshTokenTimeExpired(RefreshTokenClaimsSet refreshTokenClaimsSet) {
        LocalDate localDateExpirationTime = toLocalDate(refreshTokenClaimsSet.getExpirationTime());
        return localDateExpirationTime.isBefore(LocalDate.now());
    }

    public boolean isRefreshTokenCountExpired(RefreshTokenClaimsSet refreshTokenClaimsSet) {
        return refreshTokenClaimsSet.getCount() <= 0;
    }

    public boolean isTokenExpired(Date expirationTime) {
        LocalDate localDateExpirationTime = toLocalDate(expirationTime);
        return localDateExpirationTime.isBefore(LocalDate.now());
    }


    private SignedJWT generateSignedJWT(String tokenClaimSetJson, PrivateKey privateKey) throws JOSEException, ParseException {
        var jwtClaimsSet = JWTClaimsSet.parse(tokenClaimSetJson);
        var header = new JWSHeader(JWSAlgorithm.RS256);
        var signedJWT = new SignedJWT(header, jwtClaimsSet);
        var signer = new RSASSASigner( privateKey);
        signedJWT.sign(signer);

        return signedJWT;
    }

    private LocalDate toLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
