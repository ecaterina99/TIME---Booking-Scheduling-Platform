package com.server.organization.application;

//DTO

public record CreateUserCommand(
        String email,
        String password,
        String fullName
) {}