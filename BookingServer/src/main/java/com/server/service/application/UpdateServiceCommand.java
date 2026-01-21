package com.server.service.application;

public record UpdateServiceCommand(
        int id,
        String name,
        Integer organizationId,
        String description,
        Integer durationMinutes,
        Integer price
) {
}

