package com.server.organization.infrastructure.organizations;

import com.server.organization.domain.organizations.Organization;
import com.server.organization.domain.organizations.OrganizationEmail;
import com.server.organization.domain.organizations.OrganizationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrganizationRepository implements OrganizationRepository {

    private final OrganizationJpaRepository organizationJpaRepository;
    private final OrganizationMapper mapper;

    public JpaOrganizationRepository(OrganizationJpaRepository organizationJpaRepository, OrganizationMapper mapper) {
        this.organizationJpaRepository = organizationJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Organization> findById(int id) {
        return organizationJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Organization> findAll() {
        return organizationJpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Organization> findByEmail(OrganizationEmail email) {
        return organizationJpaRepository.findByEmail(email.value()).map(mapper::toDomain);
    }

    @Override
    public Organization save(Organization organization) {
        OrganizationJpaEntity entity = mapper.toEntity(organization);
        return mapper.toDomain(organizationJpaRepository.save(entity));
    }

    @Override
    public void delete(Organization organization) {
        organizationJpaRepository.deleteById(organization.getId());
    }
}
