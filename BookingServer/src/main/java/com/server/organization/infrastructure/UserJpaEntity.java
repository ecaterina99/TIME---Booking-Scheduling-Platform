package com.server.organization.infrastructure;

import com.server.organization.domain.enums.GlobalRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "This field can't be empty")
    private String email;
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters")
    @Column(name = "password", nullable = false)
    private String password;
    @NotEmpty(message = "This field can't be empty")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "global_role", nullable = false, columnDefinition = "DEFAULT 'USER'")
    private GlobalRole globalRole;

    public UserJpaEntity() {
    }

    public UserJpaEntity(String email, String password, String fullName, GlobalRole globalRole) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.globalRole = globalRole;
    }
}
