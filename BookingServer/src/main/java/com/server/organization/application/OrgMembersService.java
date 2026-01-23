package com.server.organization.application;

import com.server.organization.api.OrganizationMemberDTO;
import com.server.organization.domain.organizationMembers.OrganizationMember;
import com.server.organization.domain.organizationMembers.OrganizationMemberRepository;
import com.server.organization.domain.organizations.OrganizationRepository;
import com.server.organization.domain.users.UserRepository;
import com.server.organization.infrastructure.organizationMembers.OrganizationMemberMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrgMembersService {

    private final OrganizationMemberMapper memberMapper;
    private final OrganizationMemberRepository repository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public OrgMembersService(OrganizationMemberMapper memberMapper, OrganizationMemberRepository repository, OrganizationRepository organizationRepository, UserRepository userRepository) {
        this.memberMapper = memberMapper;
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
                .ifPresent(m -> {
                    throw new IllegalArgumentException("This user is already a member of this organization");
                });

        OrganizationMember member = new OrganizationMember(
                command.organizationId(),
                command.userId(),
                command.role()
        );
        return repository.save(member).getId();
    }

    @Transactional
    public void deleteMember(int organizationId, int userId) {
        organizationRepository.findById(organizationId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));

        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        OrganizationMember member = repository.findByOrganizationIdAndUserId(organizationId, userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "This user is NOT a member of this organization"
                ));

        repository.delete(member);
    }

    @Transactional(readOnly = true)
    public List<OrganizationMemberDTO> getMembers(int organizationId) {
        organizationRepository.findById(organizationId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));

        List<OrganizationMember> members = repository.findByOrganizationId(organizationId);
        return members.stream().map(memberMapper::toDTO).toList();
    }
}
