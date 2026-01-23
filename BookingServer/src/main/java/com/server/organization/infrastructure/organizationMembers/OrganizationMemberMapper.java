package com.server.organization.infrastructure.organizationMembers;

import com.server.organization.api.OrganizationMemberDTO;
import com.server.organization.domain.organizationMembers.OrganizationMember;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMemberMapper {

    public OrganizationMemberDTO toDTO(OrganizationMember member) {
        return new OrganizationMemberDTO(
                member.getId(),
                member.getOrganizationId(),
                member.getUserId(),
                member.getRole()
        );
    }

    public OrganizationMember toDomain(OrganizationMembersEntity entity) {
        return new OrganizationMember(
                entity.getId(),
                entity.getOrganizationId(),
                entity.getUserId(),
                entity.getRole()
        );
    }

    public OrganizationMembersEntity toEntity(OrganizationMember member) {
        OrganizationMembersEntity entity = new OrganizationMembersEntity(
                member.getOrganizationId(),
                member.getUserId(),
                member.getRole()
        );
        entity.setId(member.getId());
        return entity;
    }
}
