package com.server.organization.api;

public record OrganizationDTO(
        int id,
        String name,
        String city,
        String address,
        String phone,
        String email
) {
}