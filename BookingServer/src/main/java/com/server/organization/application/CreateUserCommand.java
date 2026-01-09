package com.server.organization.application;

public record CreateUserCommand(
        String email,
        String password,
        String fullName
) {}