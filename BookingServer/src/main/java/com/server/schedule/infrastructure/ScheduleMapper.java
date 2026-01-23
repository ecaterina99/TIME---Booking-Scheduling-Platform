package com.server.schedule.infrastructure;

import com.server.schedule.application.ScheduleDTO;
import com.server.schedule.application.WorkingDayDTO;
import com.server.schedule.domain.Schedule;
import com.server.schedule.domain.WorkingDay;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleMapper {

    public Schedule toDomain(List<ScheduleJpaEntity> entities) {
        if (entities.isEmpty()) {
            throw new IllegalArgumentException("Schedule rows cannot be empty");
        }
        int specialistId = entities.getFirst().getSpecialistId();

        List<WorkingDay> workingDays = entities.stream()
                .map(e -> new WorkingDay(
                        e.getDayOfWeek(),
                        e.getStartTime(),
                        e.getEndTime()
                ))
                .toList();

        return new Schedule(specialistId, workingDays);
    }

    public ScheduleDTO toDTO(Schedule schedule) {
        List<WorkingDayDTO> days = schedule.getWorkingDays().stream()
                .map(day -> new WorkingDayDTO(
                        day.getDayOfWeek(),
                        day.getStart(),
                        day.getEnd()
                ))
                .toList();

        return new ScheduleDTO(schedule.getSpecialistId(), days);
    }

    public List<ScheduleJpaEntity> toEntities(Schedule schedule) {
        return schedule.getWorkingDays().stream()
                .map(day -> new ScheduleJpaEntity(
                        schedule.getSpecialistId(),
                        day.getDayOfWeek(),
                        day.getStart(),
                        day.getEnd()
                ))
                .toList();
    }
}
