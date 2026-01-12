package com.server.organization.domain;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(UserEmail email) {
        super("User with email " + email.value() + " already exists");
    }
}