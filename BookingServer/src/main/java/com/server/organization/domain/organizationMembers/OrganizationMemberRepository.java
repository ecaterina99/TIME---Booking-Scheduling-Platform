package com.server.organization.domain.organizationMembers;

import java.util.List;
import java.util.Optional;

public interface OrganizationMemberRepository {

    Optional<OrganizationMember> findById(int id);

    List<OrganizationMember> findByOrganizationId(int organizationId);

    Optional<OrganizationMember> findByOrganizationIdAndUserId(int organizationId, int userId);

    OrganizationMember save(OrganizationMember member);

    void delete(OrganizationMember member);
}