package com.server.organization.infrastructure;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<UserJpaEntity, Integer> {
    Optional<UserJpaEntity> findByEmail(String email);
}