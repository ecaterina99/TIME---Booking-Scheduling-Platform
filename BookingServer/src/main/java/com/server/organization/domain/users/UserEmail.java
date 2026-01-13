package com.server.organization.domain.users;

public record UserEmail(String value) {
    public UserEmail {
        if (value == null || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}