package com.server.service.application;

public record AddServiceCommand(
        String name,
        int organizationId,
        String description,
        int durationMinutes,
        int price
) {
}
