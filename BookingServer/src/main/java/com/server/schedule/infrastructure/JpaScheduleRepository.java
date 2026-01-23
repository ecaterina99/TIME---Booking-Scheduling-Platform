package com.server.schedule.infrastructure;

import com.server.schedule.domain.Schedule;
import com.server.schedule.domain.ScheduleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaScheduleRepository implements ScheduleRepository {

    private final ScheduleJpaRepository scheduleJpaRepository;
    private final ScheduleMapper mapper;

    public JpaScheduleRepository(ScheduleJpaRepository scheduleJpaRepository, ScheduleMapper mapper) {
        this.scheduleJpaRepository = scheduleJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Schedule> findBySpecialistId(int specialistId) {
        List<ScheduleJpaEntity> list = scheduleJpaRepository.findBySpecialistId(specialistId);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDomain(list));
    }

    @Override
    public void save(Schedule schedule) {
        scheduleJpaRepository.deleteBySpecialistId(schedule.getSpecialistId());
        scheduleJpaRepository.saveAll(mapper.toEntities(schedule));
    }
}
