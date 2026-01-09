package com.server.organization.api;

public record UserDTO(
        int id,
        String email,
        String fullName,
        String globalRole
) {}