package com.server.organization.application;

import com.server.organization.api.OrganizationDTO;
import com.server.organization.domain.organizationMembers.OrganizationMember;
import com.server.organization.domain.organizationMembers.OrganizationMemberRepository;
import com.server.organization.domain.organizations.Organization;
import com.server.organization.domain.organizations.OrganizationRepository;
import com.server.organization.domain.users.UserRepository;
import com.server.shared.infrastructure.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrgMembersService {

    private final UserMapper organizationMapper;
    private final OrganizationMemberRepository repository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public OrgMembersService(UserMapper organizationMapper, OrganizationMemberRepository repository, OrganizationRepository organizationRepository, UserRepository userRepository) {
        this.organizationMapper = organizationMapper;
        this.repository = repository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public int addMember(AddMemberCommand command) {
        organizationRepository.findById(command.organizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));

        userRepository.findById(command.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        repository.findByOrganizationIdAndUserId(command.organizationId(), command.userId())
                .ifPresent(m -> { throw new IllegalArgumentException("This user already is a member of this organization"); });

        OrganizationMember member = new OrganizationMember(
                command.organizationId(),
                command.userId(),
                command.role()
        );

        return repository.save(member).getId();
    }
}

