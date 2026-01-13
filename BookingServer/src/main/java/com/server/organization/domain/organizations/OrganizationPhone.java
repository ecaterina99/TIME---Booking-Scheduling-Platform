package com.server.organization.domain.organizations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OrganizationPhone(
        @NotBlank(message = "Phone cannot be empty")
        @Pattern(
                regexp = "^[0-9]{7,15}$",
                message = "Phone number contains invalid characters")

        String value) {
}
