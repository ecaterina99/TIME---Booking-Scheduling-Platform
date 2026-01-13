package com.server.organization.infrastructure.users;

import com.server.organization.domain.users.User;
import com.server.organization.domain.users.UserEmail;
import com.server.organization.domain.users.UserRepository;
import com.server.shared.infrastructure.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJpaRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    UserJpaRepository(JpaUserRepository jpaUserRepository, UserMapper mapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findById(int id) {
        return jpaUserRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<User> findByEmail(UserEmail email) {
        return jpaUserRepository.findByEmail(email.value())
                .map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = mapper.toEntity(user);
        return mapper.toDomain(jpaUserRepository.save(entity));
    }

    @Override
    public void delete(User user) {
        jpaUserRepository.deleteById(user.getId());
    }
}