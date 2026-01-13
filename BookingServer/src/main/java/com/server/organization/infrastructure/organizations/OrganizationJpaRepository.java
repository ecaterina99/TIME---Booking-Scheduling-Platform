package com.server.organization.infrastructure.organizations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationJpaRepository extends JpaRepository<OrganizationJpaEntity, Integer> {
    Optional<OrganizationJpaEntity> findByEmail(String email);
}