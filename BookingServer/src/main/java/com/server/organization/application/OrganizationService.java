package com.server.organization.application;

import com.server.organization.api.OrganizationDTO;
import com.server.organization.domain.organizations.*;
import com.server.organization.domain.organizations.OrganizationAlreadyExistsException;
import com.server.shared.infrastructure.OrganizationMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;


    public OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
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
    public void updateOrganization(UpdateOrganizationCommand command) {
        Organization organization = findOrganizationById(command.id());

        if (command.name() != null) {
            organization.changeName(command.name());
        }
        if (command.email() != null && !command.email().isBlank()) {
            organization.changeEmail(new OrganizationEmail(command.email()));
        }
        if (command.address() != null && !command.address().isBlank()) {
            organization.changeAddress(new OrganizationAddress(command.address()));
        }
        if (command.city() != null) {
            organization.changeCity(command.city());
        }
        if (command.phone() != null && !command.phone().isBlank()) {
            organization.changePhone(new OrganizationPhone(command.phone()));
        }
        organizationRepository.save(organization);
    }


    @Transactional
    public void deleteOrganizationById(int id) {
        Organization organization = findOrganizationById(id);
        organizationRepository.delete(organization);
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
