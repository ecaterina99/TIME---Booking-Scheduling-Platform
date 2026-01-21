package com.server.schedule.application;

import java.util.List;

public record CreateScheduleCommand(
        int specialistId,
        List<WorkingDayDTO> workingDays
) {
}
