package com.server.organization.application;

import jakarta.validation.constraints.Pattern;

public record UpdateOrganizationCommand(
        int id,
        String name,
        String city,
        @Pattern(regexp = "^[A-Za-z0-9 .,'/-]+$",
                message = "Address contains invalid characters") String address,
        @Pattern(regexp = "^[0-9]{7,15}$",
                message = "Phone number contains invalid characters")
        String phone,
        String email
) {
}
