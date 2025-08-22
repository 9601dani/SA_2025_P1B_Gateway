package com.danimo.api_gateway.infraestructure.shared.exceptions;

public class GatewayException extends RuntimeException {
    public GatewayException(String message) {
        super(message);
    }
}
