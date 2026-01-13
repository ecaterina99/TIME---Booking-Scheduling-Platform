package com.server.organization.application;

public record CreateOrganizationCommand(
        String name,
        String city,
        String address,
        String email,
        String phone
) {}
