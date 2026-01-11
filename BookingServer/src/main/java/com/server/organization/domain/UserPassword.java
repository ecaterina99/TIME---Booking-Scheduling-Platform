package com.server.organization.domain;

public record UserPassword(String value) {
    public UserPassword {
        if (value == null || value.length() < 8)
            throw new IllegalArgumentException("Password too short");
    }
}