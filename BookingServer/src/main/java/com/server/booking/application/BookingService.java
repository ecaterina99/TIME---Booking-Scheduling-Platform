package com.server.booking.application;

import com.server.booking.domain.Booking;
import com.server.booking.domain.BookingRepository;
import com.server.booking.domain.TimeSlot;
import com.server.organization.domain.users.UserRepository;
import com.server.schedule.domain.Schedule;
import com.server.schedule.domain.ScheduleRepository;
import com.server.schedule.domain.WorkingHours;
import com.server.service.domain.ServiceRepository;
import com.server.shared.infrastructure.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final UserMapper userMapper;
    private final ScheduleRepository scheduleRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, ServiceRepository serviceRepository, UserMapper userMapper, ScheduleRepository scheduleRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.userMapper = userMapper;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional(readOnly = true)
    public BookingDTO getBooking(int id) {
        Booking booking = findBookingById(id);
        return userMapper.toDTO(booking);
    }

    @Transactional(readOnly = true)
    public List<BookingDTO> getBookingsBySpecialistId(int id) {
        List<Booking> bookings = bookingRepository.getBookingsBySpecialistId(id);
        return bookings.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookingDTO> getBookingsByClientId(int id) {
        List<Booking> bookings = bookingRepository.getBookingsByClientId(id);
        return bookings.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.getAllBookings();
        return bookings.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public int createBooking(CreateBookingCommand command) {

        userRepository.findById(command.clientId()).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        userRepository.findById(command.specialistId()).orElseThrow(() -> new EntityNotFoundException("Specialist not found"));
        serviceRepository.findById(command.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        WorkingHours requestedSlot =
                new WorkingHours(command.start(), command.end());

        Schedule schedule = scheduleRepository.findBySpecialistId(command.specialistId()).orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

        if(!schedule.isAvailable(requestedSlot)){
            throw new IllegalStateException("Specialist is not available at this time");
        }

        List<Booking>existingBooking = bookingRepository.getBookingsBySpecialistId(command.specialistId());
        boolean overlaps = existingBooking.stream().anyMatch(b -> b.getTimeSlot().overlaps(requestedSlot));

        if(overlaps){
            throw new IllegalStateException("Time slot already booked");
        }

        Booking booking = new Booking(
                0,
                command.clientId(),
                command.specialistId(),
                command.serviceId(),
                new TimeSlot(command.start(), command.end()),
                LocalDateTime.now()
        );
        bookingRepository.add(booking);
        return booking.getId();
    }


    @Transactional
    public void cancelBookingByClient(int bookingId, int clientId) throws AccessDeniedException {
        Booking booking = findBookingById(bookingId);

        if (booking.getClientId() != clientId) {
            throw new AccessDeniedException("Client does not own this booking");
        }

        booking.cancelByClient(LocalDateTime.now());
        bookingRepository.add(booking);
    }

    @Transactional
    public void cancelBookingBySpecialist(int bookingId, int specialistId) {

        Booking booking = findBookingById(bookingId);

        if (booking.getSpecialistId() != specialistId) {
            throw new SecurityException("Specialist does not own this booking");
        }

        booking.cancelBySpecialist();
        bookingRepository.add(booking);
    }


    @Transactional
    public int confirmBySpecialist(int bookingId, int specialistId) {
        Booking booking = findBookingById(bookingId);
        if (booking.getSpecialistId() != specialistId) {
            throw new SecurityException("Specialist does not own this booking");
        }
        booking.confirmBySpecialist();
        bookingRepository.add(booking);
        return booking.getId();
    }


    private Booking findBookingById(int id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Booking with id: " + id + " is not found"
        ));
    }


}
