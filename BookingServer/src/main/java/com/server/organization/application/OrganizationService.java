package com.server.organization.application;

import com.server.organization.api.OrganizationDTO;
import com.server.organization.domain.organizations.*;
import com.server.organization.domain.users.User;
import com.server.organization.domain.users.UserAlreadyExistsException;
import com.server.organization.domain.users.UserEmail;
import com.server.organization.infrastructure.organizations.OrganizationAlreadyExistsException;
import com.server.shared.infrastructure.OrganizationMapper;
import com.server.shared.infrastructure.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizationMapper organizationMapper;


    public OrganizationService(OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
        this.organizationMapper = organizationMapper;
    }


    @Transactional(readOnly = true)
    public List<OrganizationDTO> getAllOrganizations() {
        return organizationRepository.findAll().
                stream().map(organizationMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public OrganizationDTO getOrganizationById(int id) {
        Organization organization = findOrganizationById(id);
        return organizationMapper.toDTO(organization);
    }


    @Transactional
    public int createOrganization(CreateOrganizationCommand createOrganizationCommand) {

        organizationRepository.findByEmail(new OrganizationEmail(createOrganizationCommand.email())).
                ifPresent(o -> {
                    throw new OrganizationAlreadyExistsException(new OrganizationEmail(createOrganizationCommand.email()));
                });
        Organization organization = new Organization(
                0,
                createOrganizationCommand.name(),
                new OrganizationAddress(createOrganizationCommand.address()),
                createOrganizationCommand.city(),
                new OrganizationEmail(createOrganizationCommand.email()),
                new OrganizationPhone(createOrganizationCommand.phone())
        );
        return organizationRepository.save(organization).getId();

    }


    private Organization findOrganizationById(int id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Organization with id: " + id + " is not found"
                ));
    }

}
