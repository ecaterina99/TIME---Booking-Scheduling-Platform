package com.server.organization.domain.organizations;

public record OrganizationEmail(String value) {
    public OrganizationEmail {
        if (value == null || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}