package com.server.schedule.application;

import com.server.schedule.domain.WorkingDay;
import com.server.schedule.domain.Schedule;
import com.server.schedule.domain.ScheduleRepository;
import com.server.shared.infrastructure.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

   private final ScheduleRepository scheduleRepository;
   private final UserMapper userMapper;

    public ScheduleService(ScheduleRepository scheduleRepository, UserMapper userMapper) {
        this.scheduleRepository = scheduleRepository;
        this.userMapper = userMapper;
    }


    @Transactional(readOnly = true)
    public ScheduleDTO findBySpecialistId (int specialistId) {
        Schedule schedule = findScheduleBySpecialistId(specialistId);
        return userMapper.scheduleToDTO(schedule);
    }

    @Transactional
    public void createOrUpdateSchedule(CreateScheduleCommand command) {
        Schedule schedule = new Schedule(
                0,
                command.specialistId(),
                command.workingDays().stream()
                        .map(d -> new WorkingDay(
                                d.dayOfWeek(),
                                d.start(),
                                d.end()
                        ))
                        .toList()
        );
        scheduleRepository.save(schedule);
    }


    private Schedule findScheduleBySpecialistId (int specialistId) {
        return scheduleRepository.findBySpecialistId(specialistId).orElseThrow(()->
                new EntityNotFoundException("Schedule for this specialist is not found"));
    }

}
