package com.server.booking.application;

import java.time.LocalDateTime;

public record BookingDTO(
        int id,
        int clientId,
        int specialistId,
        int serviceId,
        LocalDateTime start,
        LocalDateTime end,
        String status) {
}
