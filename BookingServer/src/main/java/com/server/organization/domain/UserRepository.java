package com.server.organization.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(int id);

    List<User> findAll();

    Optional<User> findByEmail(UserEmail email);

    User save(User user);

    void delete(User user);
}