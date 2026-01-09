package com.server.organization.application;

import com.server.organization.api.UserDTO;
import com.server.organization.domain.enums.GlobalRole;
import com.server.organization.infrastructure.UserJpaEntity;
import com.server.organization.infrastructure.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public UserService(UserJpaRepository userRepository,
                       PasswordEncoder passwordEncoder, ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
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
        UserJpaEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with id: " + id + " is not found"
                ));

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

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}