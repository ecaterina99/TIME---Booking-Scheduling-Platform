
package com.server.schedule.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "schedules")
public class ScheduleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "specialist_id")
    private int specialistId;

    @Column(nullable = false, name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false, name = "start_time")
    private LocalTime startTime;
    @Column(nullable = false, name = "end_time")
    private LocalTime endTime;


    public ScheduleJpaEntity() {}

    public ScheduleJpaEntity(int specialistId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.specialistId = specialistId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
