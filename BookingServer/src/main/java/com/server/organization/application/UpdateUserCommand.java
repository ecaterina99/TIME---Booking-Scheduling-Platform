package com.server.organization.application;

import com.server.organization.domain.enums.GlobalRole;
//what service layer receives as an application use-case instruction.
public record UpdateUserCommand(
        String email,
        String fullName,
        GlobalRole globalRole,
        int id
) {
}