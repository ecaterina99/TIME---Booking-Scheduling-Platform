package com.server.booking.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaBookingRepository extends JpaRepository<BookingJpaEntity, Integer> {

    List<BookingJpaEntity> getBookingsByClientId(int clientId);

    List<BookingJpaEntity> getBookingsBySpecialistId(int specialistId);
}
