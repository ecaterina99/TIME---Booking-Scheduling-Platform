package com.server.booking.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Booking {

    private int id;
    private Integer clientId;
    private Integer specialistId;
    private Integer serviceId;
    private TimeSlot timeSlot;
    private String status;
    private LocalDateTime createdAt;

    public Booking(int id, Integer clientId, Integer specialistId, Integer serviceId, TimeSlot timeSlot, String status, LocalDateTime createdAt) {
        if (specialistId == null || serviceId == null || clientId == null)
            throw new IllegalArgumentException("Ids must not be null");
        if (status == null)
            throw new IllegalArgumentException("Status must not be null");

        this.id = id;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.specialistId = specialistId;
        this.timeSlot = timeSlot;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Booking(Integer clientId, Integer specialistId, Integer serviceId, TimeSlot timeSlot, String status, LocalDateTime createdAt) {
        this.clientId = clientId;
        this.specialistId = specialistId;
        this.serviceId = serviceId;
        this.timeSlot = timeSlot;
        this.status = status;
        this.createdAt = createdAt;
    }

}
