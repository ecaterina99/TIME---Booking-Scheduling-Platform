package com.server.schedule.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository {

    Optional<Schedule> findBySpecialistId(int specialistId);

    void save(Schedule schedule);
}
