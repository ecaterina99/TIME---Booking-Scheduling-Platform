package com.server.schedule.infrastructure;

import com.server.schedule.domain.Schedule;
import com.server.schedule.domain.ScheduleRepository;
import com.server.shared.infrastructure.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaScheduleRepository implements ScheduleRepository {

    private final JpaRepository jpaRepository;
    private final UserMapper mapper;

    public JpaScheduleRepository(JpaRepository jpaRepository, UserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Schedule> findBySpecialistId(int specialistId) {
        List <ScheduleJpaEntity> list = jpaRepository.findBySpecialistId(specialistId);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toScheduleDomain(list));
    }

    @Override
    public void save(Schedule schedule) {
        jpaRepository.deleteBySpecialistId(schedule.getSpecialistId());
        jpaRepository.saveAll(mapper.toScheduleEntities(schedule));
    }
}
