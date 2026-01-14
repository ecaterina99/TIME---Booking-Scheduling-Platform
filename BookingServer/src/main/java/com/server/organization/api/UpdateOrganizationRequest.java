package com.server.organization.api;

import jakarta.validation.constraints.Pattern;

public record UpdateOrganizationRequest(
        String name,
        String city,
        @Pattern(regexp = "^[A-Za-z0-9 .,'/-]+$",
                message = "Address contains invalid characters") String address,
        String email,
        @Pattern(regexp = "^[0-9]{7,15}$",
                message = "Phone number contains invalid characters")
        String phone
) {
}