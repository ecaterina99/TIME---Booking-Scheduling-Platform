/*package com.server.shared.infrastructure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityCurrentUserProvider
        implements CurrentUserProvider {

    @Override
    public int getUserId() {
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserPrincipal principal =
                (CustomUserPrincipal) auth.getPrincipal();

        return principal.getUserId();
    }

    @Override
    public boolean hasRole(String role) {
        return auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(role));
    }
}

 */