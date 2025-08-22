package com.danimo.api_gateway.infraestructure.segurity.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.danimo.api_gateway.ports.segurity.TokenVerifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JwtTokenVerifier implements TokenVerifier {

    private final TokenSettings tokenSettings;
    private final JWTVerifier verifier;

    public JwtTokenVerifier(TokenSettings tokenSettings) {
        this.tokenSettings = tokenSettings;
        Algorithm algorithm = Algorithm.HMAC256(tokenSettings.getJwtSecret());
        this.verifier = JWT.require(algorithm).build();
    }

    @Override
    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }

    @Override
    public LocalDateTime expiresAt(String token) {
        DecodedJWT jwt = verify(token);
        return jwt.getExpiresAt()
                .toInstant()
                .atZone(ZoneId.of(tokenSettings.getZone()))
                .toLocalDateTime();
    }

    @Override
    public boolean isExpired(String token) {
        try {
            return LocalDateTime.now().isAfter(expiresAt(token));
        } catch (Exception e) {
            return true;
        }
    }
}
