package com.server.schedule.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Schedule {

    private int id;
    private final int specialistId;
    private final List<WorkingDay> workingDays;

    public Schedule(int id, int specialistId, List<WorkingDay> workingDays) {
        this.id = id;
        this.specialistId = specialistId;
        this.workingDays = List.copyOf(workingDays);
    }

    public Schedule(int specialistId, List<WorkingDay> workingDays) {
        this.specialistId = specialistId;
        this.workingDays = List.copyOf(workingDays);
    }

    public boolean isAvailable(WorkingHours slot) {
        return workingDays.stream().anyMatch(day -> day.allows(slot));
    }
}
