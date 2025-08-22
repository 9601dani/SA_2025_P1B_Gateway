package com.danimo.api_gateway.infraestructure.routing.spring;

import com.danimo.api_gateway.ports.segurity.TokenVerifier;
import com.danimo.api_gateway.infraestructure.shared.exceptions.ForbiddenException;
import com.danimo.api_gateway.infraestructure.shared.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtVerifyFilterFactory extends AbstractGatewayFilterFactory<JwtVerifyFilterFactory.Config> {

    private final TokenVerifier tokenVerifier;

    public JwtVerifyFilterFactory(TokenVerifier tokenVerifier) {
        super(Config.class);
        this.tokenVerifier = tokenVerifier;
    }

    @Override
    public GatewayFilter apply(JwtVerifyFilterFactory.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            String authHeader = request.getHeaders().getFirst("Authorization");

            if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ForbiddenException("Token not found");
            }

            String token = authHeader.substring(7);
            if(this.tokenVerifier.isExpired(token)) {
                throw new TokenExpiredException("Token expired");
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {}
}
