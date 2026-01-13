package com.server.organization.domain.organizations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OrganizationAddress(
        @NotBlank(message = "Address cannot be empty")
        @Size(max = 200, message = "Address must be <= 200 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9 .,'/-]+$",
                message = "Address contains invalid characters")

        String value) {
}

