package com.server.organization.application;

import com.server.organization.domain.enums.Role;

public record AddMemberCommand(
        int organizationId,
        int userId,
        Role role
) {}
