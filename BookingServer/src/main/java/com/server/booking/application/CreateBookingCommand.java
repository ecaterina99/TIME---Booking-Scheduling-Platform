package com.server.booking.application;

import java.time.LocalDateTime;

public record CreateBookingCommand(
        int clientId,
        int specialistId,
        int serviceId,
        LocalDateTime start,
        LocalDateTime end,
        LocalDateTime createdAt
) {
}
