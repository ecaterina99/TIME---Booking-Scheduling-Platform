package com.server.organization.domain;

import com.server.organization.domain.enums.GlobalRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private GlobalRole globalRole;
}