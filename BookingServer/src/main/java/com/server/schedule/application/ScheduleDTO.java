package com.server.schedule.application;

import java.util.List;

public record ScheduleDTO(
        int specialistId,
        List<WorkingDayDTO> workingDays
) {
}
