package com.server.shared.infrastructure;

import com.server.organization.api.OrganizationDTO;
import com.server.organization.domain.organizations.Organization;
import com.server.organization.domain.organizations.OrganizationAddress;
import com.server.organization.domain.organizations.OrganizationEmail;
import com.server.organization.domain.organizations.OrganizationPhone;
import com.server.organization.infrastructure.organizations.OrganizationJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public OrganizationJpaEntity toEntity(Organization organization) {
        OrganizationJpaEntity entity = new OrganizationJpaEntity(
                organization.getName(),
                organization.getEmail().value(),
                organization.getCity(),
                organization.getAddress().value(),
                organization.getPhone().value()

                );
        entity.setId(organization.getId());
        return entity;
    }

    public Organization toDomain(OrganizationJpaEntity entity) {
        return new Organization(
                entity.getId(),
                entity.getName(),
                new OrganizationAddress(entity.getAddress()),
                entity.getCity(),
                new OrganizationEmail(entity.getEmail()),
                new OrganizationPhone(entity.getPhone())
        );
    }

    public OrganizationDTO toDTO(Organization organization) {
        return new OrganizationDTO(
                organization.getId(),
                organization.getName(),
                organization.getCity(),
                organization.getAddress().value(),
                organization.getPhone().value(),
                organization.getEmail().value()
        );
    }
}


