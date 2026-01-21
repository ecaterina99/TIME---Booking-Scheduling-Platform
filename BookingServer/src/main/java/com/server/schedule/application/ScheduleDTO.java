package com.server.schedule.application;

import java.util.List;

public record ScheduleDTO(
        int id,
        int specialistId,
        List<WorkingDayDTO> workingDays
) {
}
