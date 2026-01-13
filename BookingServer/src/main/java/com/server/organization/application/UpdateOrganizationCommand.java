package com.server.organization.application;

public record UpdateOrganizationCommand(
        int id,
        String name,
        String city,
        String address,
        String phone,
        String email
) {
}
