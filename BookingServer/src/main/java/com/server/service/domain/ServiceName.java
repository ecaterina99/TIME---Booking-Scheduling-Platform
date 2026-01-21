package com.server.service.domain;

public record ServiceName(String value) {
    public ServiceName {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Service name must not be empty");
        if (value.length() > 255)
            throw new IllegalArgumentException("Service name too long");
    }
}
