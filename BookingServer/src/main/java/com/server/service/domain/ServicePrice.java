package com.server.service.domain;

public record ServicePrice(int price) {
    public ServicePrice {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }
}
