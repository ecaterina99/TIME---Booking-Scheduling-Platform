package com.server.booking.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository {

    Optional<Booking> findById(int id);

    List<Booking> getAllBookings();

    List<Booking> getBookingsBySpecialistId(int specialistId);

    List<Booking> getBookingsByClientId(int clientId);

    Booking add(Booking booking);

    void delete(int bookingId);
}
