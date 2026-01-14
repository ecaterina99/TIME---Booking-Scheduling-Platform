package com.server.organization.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateOrganizationCommand(
        @NotBlank String name,
        @NotBlank String city,
        @NotBlank
        @Size(max = 200)
        @Pattern(regexp = "^[A-Za-z0-9 .,'/-]+$",
                message = "Address contains invalid characters") String address,
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "^[0-9]{7,15}$",
                message = "Phone number contains invalid characters")
        String phone
) {
}
