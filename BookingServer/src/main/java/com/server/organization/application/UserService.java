package com.server.organization.application;

import com.server.organization.api.UserDTO;
import com.server.organization.domain.User;
import com.server.organization.domain.UserEmail;
import com.server.organization.domain.UserPassword;
import com.server.organization.domain.UserRepository;
import com.server.organization.domain.enums.GlobalRole;
import com.server.shared.infrastructure.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, UserMapper userMapper) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(int id) {
        User user = findUserById(id);
        return userMapper.toDTO(user);
    }

    @Transactional
    public int createUser(CreateUserCommand command) {

        userRepository.findByEmail(new UserEmail(command.email()))
                .ifPresent(u -> {
                    throw new IllegalArgumentException("User with this email already exists");
                });
        User user = new User(
                0,
                new UserEmail(command.email()),
                new UserPassword(passwordEncoder.encode(command.password())),
                command.fullName(),
                GlobalRole.USER
        );

        return userRepository.save(user).getId();
    }

    @Transactional
    public void deleteUser(int id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    @Transactional
    public void updateUser(UpdateUserCommand command) {

        User user = findUserById(command.id());

        if (command.fullName() != null) {
            user.changeFullName(command.fullName());
        }
        if (command.email() != null && !command.email().isBlank()) {
            user.changeEmail(new UserEmail(command.email()));
        }
        if (command.globalRole() != null) {
            user.changeRole(command.globalRole());
        }
        userRepository.save(user);
    }

    private User findUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with id: " + id + " is not found"
                ));
    }
}