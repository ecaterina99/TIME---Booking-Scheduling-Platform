package com.server.organization.infrastructure.users;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends org.springframework.data.jpa.repository.JpaRepository<UserJpaEntity, Integer> {
    Optional<UserJpaEntity> findByEmail(String email);
}