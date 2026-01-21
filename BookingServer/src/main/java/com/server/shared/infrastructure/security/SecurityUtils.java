/*
package com.server.shared.infrastructure;

import com.server.organization.domain.enums.GlobalRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    CustomUserPrincipal principal = (CustomUserPrincipal) auth.getPrincipal();

    int userId = principal.getUserId();
    GlobalRole role = principal.getRole();
}


 */