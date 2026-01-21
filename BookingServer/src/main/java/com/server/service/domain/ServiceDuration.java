package com.server.service.domain;

public record ServiceDuration(int minutes) {

    public ServiceDuration {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (minutes > 8 * 60) {
            throw new IllegalArgumentException("Duration is too long");
        }
    }
}
