package com.server.organization.application;

import jakarta.validation.constraints.NotBlank;

public record CreateUserCommand(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String fullName
) {}