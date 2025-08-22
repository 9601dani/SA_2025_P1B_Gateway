package com.danimo.api_gateway.ports.segurity;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDateTime;

public interface TokenVerifier {
    DecodedJWT verify(String token) throws JWTVerificationException;
    LocalDateTime expiresAt(String token) throws JWTVerificationException;
    boolean isExpired(String token);
}
