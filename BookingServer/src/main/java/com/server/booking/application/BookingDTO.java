package com.server.booking.application;

import com.server.booking.domain.TimeSlot;

import java.time.LocalDateTime;

public record BookingDTO (
    int id,
    int clientId,
    int specialistId,
    int serviceId,
    LocalDateTime start,
    LocalDateTime end,
    String status,
    LocalDateTime createdAt
)
{ }
