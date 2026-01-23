package com.server.booking.infrastructure;

import com.server.booking.application.BookingDTO;
import com.server.booking.domain.Booking;
import com.server.booking.domain.TimeSlot;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingJpaEntity toEntity(Booking booking) {
        BookingJpaEntity entity = new BookingJpaEntity(
                booking.getClientId(),
                booking.getServiceId(),
                booking.getSpecialistId(),
                booking.getTimeSlot().start(),
                booking.getTimeSlot().end(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
        entity.setId(booking.getId());
        return entity;
    }

    public Booking toDomain(BookingJpaEntity entity) {
        return new Booking(
                entity.getId(),
                entity.getClientId(),
                entity.getSpecialistId(),
                entity.getServiceId(),
                new TimeSlot(entity.getStartTime(), entity.getEndTime()),
                entity.getCreatedAt()
        );
    }

    public BookingDTO toDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getClientId(),
                booking.getSpecialistId(),
                booking.getServiceId(),
                booking.getTimeSlot().start(),
                booking.getTimeSlot().end(),
                booking.getStatus().name()
        );
    }
}
