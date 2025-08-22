package com.danimo.api_gateway.infraestructure.segurity.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenSettings {
    private final String jwtSecret;
    private final Integer accessTokenExpiration;
    private final Integer refreshTokenExpiration;
    private final String zone;

}
