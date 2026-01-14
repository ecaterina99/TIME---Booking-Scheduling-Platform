package com.server.organization.api;

import com.server.organization.application.CreateOrganizationCommand;
import com.server.organization.application.CreateUserCommand;
import com.server.organization.application.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@Tag(name = "Organizations", description = "Operations related to organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @GetMapping
    @Operation(summary = "Retrieve all organizations")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all organizations",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = OrganizationDTO.class))))
    public List<OrganizationDTO> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping
    @Operation(summary = "Register a new organization")
    @ApiResponse(responseCode = "201", description = "Organization created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrganizationDTO.class)))
    public int registerOrganization(@Valid @RequestBody CreateOrganizationCommand command) {
        return organizationService.createOrganization(command);
    }
}
