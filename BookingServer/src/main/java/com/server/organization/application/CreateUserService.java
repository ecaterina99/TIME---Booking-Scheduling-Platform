package com.server.organization.application;

import com.server.organization.domain.enums.GlobalRole;
import com.server.organization.infrastructure.UserJpaEntity;
import com.server.organization.infrastructure.UserJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService {

    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserJpaRepository userRepository,
                             PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public int createUser(CreateUserCommand command) {

        userRepository.findByEmail(command.email())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already in use");
                });

        UserJpaEntity entity = new UserJpaEntity(
                command.email(),
                passwordEncoder.encode(command.password()),
                command.fullName(),
                GlobalRole.USER
        );

        return userRepository.save(entity).getId();
    }
}