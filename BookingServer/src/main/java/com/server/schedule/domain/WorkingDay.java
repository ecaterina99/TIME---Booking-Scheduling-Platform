package com.server.schedule.domain;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class WorkingDay {

    private final java.time.DayOfWeek dayOfWeek;
    private final LocalTime start;
    private final LocalTime end;

    public WorkingDay(java.time.DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
    }

    public boolean allows(WorkingHours slot) {
        if (!slot.getStart().getDayOfWeek().equals(dayOfWeek)) {
            return false;
        }

        return !slot.getStart().toLocalTime().isBefore(start)
                && !slot.getEnd().toLocalTime().isAfter(end);
    }
}