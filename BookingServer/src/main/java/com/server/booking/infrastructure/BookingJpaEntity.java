package com.server.booking.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "bookings")
public class BookingJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "client_id")
    private int clientId;
    @Column(nullable = false, name = "service_id")
    private int serviceId;
    @Column(nullable = false, name = "specialist_id")
    private int specialistId;
    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;
    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;
    private String status;
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    public BookingJpaEntity() {
    }

    public BookingJpaEntity(int clientId, int serviceId, int specialistId, LocalDateTime startTime, LocalDateTime endTime, String status, LocalDateTime createdAt) {
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.specialistId = specialistId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
    }

}
