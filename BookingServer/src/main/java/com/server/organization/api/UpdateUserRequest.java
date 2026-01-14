package com.server.organization.api;

import com.server.organization.domain.enums.GlobalRole;

public record UpdateUserRequest(
        String email,
        String fullName,
        GlobalRole globalRole
) {
}