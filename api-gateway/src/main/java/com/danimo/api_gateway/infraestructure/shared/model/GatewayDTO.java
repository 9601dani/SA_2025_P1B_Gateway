package com.danimo.api_gateway.infraestructure.shared.model;

import java.util.List;

public record GatewayDTO (String sub, List<String> roles) {}
