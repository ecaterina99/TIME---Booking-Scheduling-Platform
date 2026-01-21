package com.server.service.api;

public record CreateServiceRequest(
        String name,
        Integer organizationId,
        String description,
        Integer durationMinutes,
        Integer price
) {
}
