package com.server.organization.application;

import com.server.organization.api.UserDTO;
import com.server.organization.domain.enums.GlobalRole;
import com.server.organization.infrastructure.UserJpaEntity;
import com.server.organization.infrastructure.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserJpaRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getFullName(),
                        user.getGlobalRole().name()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(int id) {
        UserJpaEntity user = findUserById(id);
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getGlobalRole().name()
        );
    }

    @Transactional
    public int createUser(CreateUserCommand command) {

        userRepository.findByEmail(command.email())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("User with this email already exists");
                });
        UserJpaEntity entity = new UserJpaEntity(
                command.email(),
                passwordEncoder.encode(command.password()),
                command.fullName(),
                GlobalRole.USER
        );
        return userRepository.save(entity).getId();
    }

    @Transactional
    public void deleteUser(int id) {
        UserJpaEntity user = findUserById(id);
        userRepository.deleteById(user.getId());
    }

    @Transactional
    public void updateUser(UpdateUserCommand command) {

        UserJpaEntity user = findUserById(command.id());

        if (command.fullName() != null) {
            user.setFullName(command.fullName());
        }
        if (command.email() != null) {
            user.setEmail(command.email());
        }
        if (command.globalRole() != null) {
            user.setGlobalRole(command.globalRole());
        }
        userRepository.save(user);
    }

    private UserJpaEntity findUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with id: " + id + " is not found"
                ));
    }
}