package com.server.organization.infrastructure.organizationMembers;

import com.server.organization.domain.organizationMembers.OrganizationMember;
import com.server.organization.domain.organizationMembers.OrganizationMemberRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrgMembersRepository implements OrganizationMemberRepository {

    private final OrgMemberRepositoryJpa orgMemberRepositoryJpa;
    private final OrganizationMemberMapper mapper;

    public JpaOrgMembersRepository(OrgMemberRepositoryJpa orgMemberRepositoryJpa, OrganizationMemberMapper mapper) {
        this.orgMemberRepositoryJpa = orgMemberRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public Optional<OrganizationMember> findById(int id) {
        return orgMemberRepositoryJpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<OrganizationMember> findByOrganizationId(int organizationId) {
        return orgMemberRepositoryJpa.findByOrganizationId(organizationId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<OrganizationMember> findByOrganizationIdAndUserId(int organizationId, int memberId) {
        return orgMemberRepositoryJpa.findByOrganizationIdAndUserId(organizationId, memberId).map(mapper::toDomain);
    }

    @Override
    public OrganizationMember save(OrganizationMember member) {
        OrganizationMembersEntity entity = mapper.toEntity(member);
        return mapper.toDomain(orgMemberRepositoryJpa.save(entity));
    }

    @Override
    public void delete(OrganizationMember member) {
        orgMemberRepositoryJpa.deleteById(member.getId());
    }
}
