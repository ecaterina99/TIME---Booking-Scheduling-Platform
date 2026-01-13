package com.server.organization.infrastructure.organizations;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "organizations")
public class OrganizationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "This field can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "This field can't be empty")
    private String email;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


    public OrganizationJpaEntity() {}

    public OrganizationJpaEntity(String name, String email, String city, String address, String phone) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

}
