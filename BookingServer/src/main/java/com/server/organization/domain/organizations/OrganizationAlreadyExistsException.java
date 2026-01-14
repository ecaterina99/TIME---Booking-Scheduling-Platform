package com.server.organization.domain.organizations;

public class OrganizationAlreadyExistsException extends RuntimeException {
    public OrganizationAlreadyExistsException(OrganizationEmail email) {
        super("Organization with email " + email.value() + " already exists");
    }
}