package com.server.organization.api;

import com.server.organization.domain.enums.GlobalRole;
//what comes from the outside world (API HTTP request)
public record UpdateUserRequest(
        String email,
        String fullName,
        GlobalRole globalRole
) {}