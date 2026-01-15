package com.server.organization.domain.organizationMembers;

import com.server.organization.domain.enums.Role;
import lombok.Getter;

@Getter
public class OrganizationMember {
    private final int id;
    private final int organizationId;
    private final int userId;
    private Role role;

    public OrganizationMember(int id, int organizationId, int userId, Role role) {
        this.id = id;
        this.organizationId = organizationId;
        this.userId = userId;
        this.role = role;
    }

    public void changeRole(Role newRole) {
        if (newRole == null) throw new IllegalArgumentException("Role must not be null");
        this.role = newRole;
    }

    public OrganizationMember(int organizationId, int userId, Role role) {
        this(0, organizationId, userId, role);
    }
}