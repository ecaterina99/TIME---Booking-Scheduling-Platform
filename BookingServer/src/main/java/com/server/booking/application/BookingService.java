package com.server.booking.application;

import com.server.booking.domain.Booking;
import com.server.booking.domain.BookingRepository;
import com.server.booking.domain.TimeSlot;
import com.server.organization.domain.organizations.Organization;
import com.server.shared.infrastructure.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserMapper userMapper;

    public BookingService(BookingRepository bookingRepository, UserMapper userMapper) {
        this.bookingRepository = bookingRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public BookingDTO getBooking(int id) {
        Booking booking = findBookingById(id);
        return userMapper.toDTO(booking);
    }

    @Transactional(readOnly = true)
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.getAllBookings();
        return bookings.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public int createBooking(CreateBookingCommand command) {
        Booking booking = new Booking(
                0,
                command.clientId(),
                command.specialistId(),
                command.serviceId(),
                new TimeSlot(command.start(), command.end()),
                command.status(),
                command.createdAt()
        );
        bookingRepository.add(booking);
        return booking.getId();
    }

    @Transactional
    public void deleteBooking(int id) {
        Booking booking = findBookingById(id);
        bookingRepository.delete(booking.getId());
    }

    private Booking findBookingById(int id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Booking with id: " + id + " is not found"
        ));
    }


}
