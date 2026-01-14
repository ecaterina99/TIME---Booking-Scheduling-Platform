package com.server.organization.infrastructure.organizations;

import com.server.organization.domain.organizations.OrganizationEmail;

public class OrganizationAlreadyExistsException extends RuntimeException {
    public OrganizationAlreadyExistsException(OrganizationEmail email) {
        super("Organization with email " + email.value() + " already exists");
    }
}