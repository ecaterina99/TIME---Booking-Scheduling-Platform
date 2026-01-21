package com.server.schedule.infrastructure;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<ScheduleJpaEntity, Integer> {
    List<ScheduleJpaEntity> findBySpecialistId(int specialistId);
    void deleteBySpecialistId(int specialistId);
}

