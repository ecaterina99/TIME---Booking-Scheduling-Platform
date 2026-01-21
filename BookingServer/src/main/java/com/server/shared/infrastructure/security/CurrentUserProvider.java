package com.server.shared.infrastructure.security;

public interface CurrentUserProvider {
    int getUserId();
    boolean hasRole(String role);
}
