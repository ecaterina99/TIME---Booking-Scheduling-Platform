package com.server.booking.domain;

import com.server.schedule.domain.WorkingHours;

import java.time.Duration;
import java.time.LocalDateTime;

public record TimeSlot(LocalDateTime start, LocalDateTime end) {

    public TimeSlot {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end must not be null");
        }
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("End must be after start");
        }
    }

    public boolean overlaps(WorkingHours other) {
        return start.isBefore(other.getEnd()) && end.isAfter(other.getStart());
    }

    public Duration toDuration() {
        return Duration.between(start, end);
    }

}
