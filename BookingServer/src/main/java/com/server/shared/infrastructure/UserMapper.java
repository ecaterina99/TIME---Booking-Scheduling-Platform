package com.server.shared.infrastructure;

import com.server.organization.api.UserDTO;
import com.server.organization.domain.users.User;
import com.server.organization.domain.users.UserEmail;
import com.server.organization.domain.users.UserPassword;
import com.server.organization.infrastructure.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail().value(),
                user.getFullName(),
                user.getGlobalRole().name()
        );
    }

    public UserJpaEntity toEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity(
                user.getEmail().value(),
                user.getPassword().value(),
                user.getFullName(),
                user.getGlobalRole()
        );
        entity.setId(user.getId());
        return entity;
    }

    public User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                new UserEmail(entity.getEmail()),
                new UserPassword(entity.getPassword()),
                entity.getFullName(),
                entity.getGlobalRole()
        );
    }
}