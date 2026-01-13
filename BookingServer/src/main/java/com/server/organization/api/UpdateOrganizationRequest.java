package com.server.organization.api;

public record UpdateOrganizationRequest(
        String name,
        String city,
        String address,
        String email,
        String phone
) {}