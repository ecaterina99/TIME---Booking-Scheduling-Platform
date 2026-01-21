package com.server.schedule.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkingHours {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public WorkingHours(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End must be after start");
        }
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(WorkingHours other) {
        return start.isBefore(other.end) && end.isAfter(other.start);
    }
}