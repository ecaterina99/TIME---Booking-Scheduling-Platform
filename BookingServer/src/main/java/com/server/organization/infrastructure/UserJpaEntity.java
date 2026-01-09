/**
 * Database entity
 */

package com.server.organization.infrastructure;

import com.server.organization.domain.enums.GlobalRole;
import jakarta.persistence.*;
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

    private String email;
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    private GlobalRole globalRole;

    public UserJpaEntity() {
    }

    public UserJpaEntity(String email, String password, String fullName, GlobalRole globalRole) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.globalRole = globalRole;
    }

    public void  setId(int id) {
        this.id = id;
    }
    public void  setEmail(String email) {
        this.email = email;
    }
    public void  setPassword(String password) {
        this.password = password;
    }
    public void  setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void  setGlobalRole(GlobalRole globalRole) {
        this.globalRole = globalRole;
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getFullName() {
        return fullName;

    }

    public GlobalRole getGlobalRole() {
        return globalRole;
    }

}
