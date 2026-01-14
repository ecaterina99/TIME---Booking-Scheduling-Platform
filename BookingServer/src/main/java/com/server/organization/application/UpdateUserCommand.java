package com.server.organization.application;

import com.server.organization.domain.enums.GlobalRole;

public record UpdateUserCommand(
        String email,
        String fullName,
        GlobalRole globalRole,
        int id
) {
}