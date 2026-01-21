package com.server.schedule.application;


import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkingDayDTO(
        DayOfWeek dayOfWeek,
        LocalTime start,
        LocalTime end
) {
}