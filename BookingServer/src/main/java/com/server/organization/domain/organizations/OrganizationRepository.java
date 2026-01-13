package com.server.organization.domain.organizations;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {
    Optional<Organization> findById(int id);

    List<Organization> findAll();

    Optional<Organization> findByEmail(OrganizationEmail email);

    Organization save(Organization organization);

    void delete(Organization organization);
}
