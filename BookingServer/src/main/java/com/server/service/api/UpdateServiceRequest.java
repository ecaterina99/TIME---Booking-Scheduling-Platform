package com.server.service.api;

public record UpdateServiceRequest(
        String name,
        Integer organizationId,
        String description,
        Integer durationMinutes,
        Integer price
) {
}

