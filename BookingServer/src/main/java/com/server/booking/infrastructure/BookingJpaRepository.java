package com.server.booking.infrastructure;

import com.server.booking.domain.Booking;
import com.server.booking.domain.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingJpaRepository implements BookingRepository {

    private final JpaBookingRepository jpaBookingRepository;
    private final BookingMapper mapper;

    public BookingJpaRepository(JpaBookingRepository jpaBookingRepository, BookingMapper mapper) {
        this.jpaBookingRepository = jpaBookingRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Booking> findById(int id) {
        return jpaBookingRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Booking> getAllBookings() {
        return jpaBookingRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Booking> getBookingsBySpecialistId(int specialistId) {
        return jpaBookingRepository.getBookingsBySpecialistId(specialistId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Booking> getBookingsByClientId(int clientId) {
        return jpaBookingRepository.getBookingsByClientId(clientId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Booking add(Booking booking) {
        BookingJpaEntity bookingJpaEntity = mapper.toEntity(booking);
        return mapper.toDomain(jpaBookingRepository.save(bookingJpaEntity));
    }

    @Override
    public void delete(int bookingId) {
        jpaBookingRepository.deleteById(bookingId);
    }
}
