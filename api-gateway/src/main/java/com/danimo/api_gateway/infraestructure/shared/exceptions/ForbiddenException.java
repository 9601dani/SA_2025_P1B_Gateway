package com.danimo.api_gateway.infraestructure.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends GatewayException {
    public ForbiddenException(String message) {
        super(message);
    }
}
