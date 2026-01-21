package com.server.shared.infrastructure;

import com.server.booking.application.BookingDTO;
import com.server.booking.domain.Booking;
import com.server.booking.domain.TimeSlot;
import com.server.booking.infrastructure.BookingJpaEntity;
import com.server.organization.api.OrganizationDTO;
import com.server.organization.api.OrganizationMemberDTO;
import com.server.organization.api.UserDTO;
import com.server.organization.domain.organizationMembers.OrganizationMember;
import com.server.organization.domain.organizations.Organization;
import com.server.organization.domain.organizations.OrganizationAddress;
import com.server.organization.domain.organizations.OrganizationEmail;
import com.server.organization.domain.organizations.OrganizationPhone;
import com.server.organization.domain.users.User;
import com.server.organization.domain.users.UserEmail;
import com.server.organization.domain.users.UserPassword;
import com.server.organization.infrastructure.organizationMembers.OrganizationMembersEntity;
import com.server.organization.infrastructure.organizations.OrganizationJpaEntity;
import com.server.organization.infrastructure.users.UserJpaEntity;
import com.server.schedule.application.ScheduleDTO;
import com.server.schedule.application.WorkingDayDTO;
import com.server.schedule.domain.Schedule;
import com.server.schedule.domain.WorkingDay;
import com.server.schedule.infrastructure.ScheduleJpaEntity;
import com.server.service.api.ServiceDTO;
import com.server.service.domain.Service;
import com.server.service.domain.ServiceDuration;
import com.server.service.domain.ServiceName;
import com.server.service.domain.ServicePrice;
import com.server.service.infrastructure.ServiceJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail().value(),
                user.getFullName(),
                user.getGlobalRole().name()
        );
    }

    public UserJpaEntity toEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity(
                user.getEmail().value(),
                user.getPassword().value(),
                user.getFullName(),
                user.getGlobalRole()
        );
        entity.setId(user.getId());
        return entity;
    }

    public User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                new UserEmail(entity.getEmail()),
                new UserPassword(entity.getPassword()),
                entity.getFullName(),
                entity.getGlobalRole()
        );
    }

    public OrganizationJpaEntity toEntity(Organization organization) {
        OrganizationJpaEntity entity = new OrganizationJpaEntity(
                organization.getName(),
                organization.getEmail().value(),
                organization.getCity(),
                organization.getAddress().value(),
                organization.getPhone().value()

        );
        entity.setId(organization.getId());
        return entity;
    }

    public Organization toDomain(OrganizationJpaEntity entity) {
        return new Organization(
                entity.getId(),
                entity.getName(),
                new OrganizationAddress(entity.getAddress()),
                entity.getCity(),
                new OrganizationEmail(entity.getEmail()),
                new OrganizationPhone(entity.getPhone())
        );
    }

    public OrganizationDTO toDTO(Organization organization) {
        return new OrganizationDTO(
                organization.getId(),
                organization.getName(),
                organization.getCity(),
                organization.getAddress().value(),
                organization.getPhone().value(),
                organization.getEmail().value()
        );
    }

    public OrganizationMemberDTO toDTO(OrganizationMember organizationMember) {
        return new OrganizationMemberDTO(
                organizationMember.getId(),
                organizationMember.getOrganizationId(),
                organizationMember.getUserId(),
                organizationMember.getRole()
        );
    }

    public OrganizationMember ToDomain(OrganizationMembersEntity e) {
        return new OrganizationMember(
                e.getId(),
                e.getOrganizationId(),
                e.getUserId(),
                e.getRole());
    }

    public OrganizationMembersEntity ToEntity(OrganizationMember m) {
        OrganizationMembersEntity e = new OrganizationMembersEntity(
                m.getOrganizationId(),
                m.getUserId(),
                m.getRole()
        );
        e.setId(m.getId());
        return e;
    }


    public Service toDomain(ServiceJpaEntity e) {
        return new Service(
                e.getId(),
                new ServiceName(e.getName()),
                e.getOrganizationId(),
                e.getDescription(),
                new ServiceDuration(e.getDuration()),
                new ServicePrice(e.getPrice())
        );
    }

    public ServiceJpaEntity toEntity(Service s) {
        ServiceJpaEntity e = new ServiceJpaEntity(
                s.getName().value(),
                s.getOrganizationId(),
                s.getDescription(),
                s.getDurationMinutes().minutes(),
                s.getPrice().price()
        );
        e.setId(s.getId());
        return e;
    }

    public ServiceDTO toDTO(Service service) {
        return new ServiceDTO(
                service.getId(),
                service.getName().value(),
                service.getOrganizationId(),
                service.getDescription(),
                service.getDurationMinutes().minutes(),
                service.getPrice().price()
        );
    }

    public BookingJpaEntity toEntity(Booking booking) {

        BookingJpaEntity b = new BookingJpaEntity(
                booking.getClientId(),
                booking.getServiceId(),
                booking.getSpecialistId(),
                booking.getTimeSlot().start(),
                booking.getTimeSlot().end(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
        b.setId(booking.getId());
        return b;
    }


    public Booking toDomain(BookingJpaEntity entity) {
        return new Booking(
                entity.getId(),
                entity.getClientId(),
                entity.getSpecialistId(),
                entity.getServiceId(),
                new TimeSlot(entity.getStartTime(), entity.getEndTime()),
                entity.getCreatedAt()
        );
    }

    public BookingDTO toDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getClientId(),
                booking.getSpecialistId(),
                booking.getServiceId(),
                booking.getTimeSlot().start(),
                booking.getTimeSlot().end(),
                booking.getStatus().name());
    }

    public Schedule toScheduleDomain (List<ScheduleJpaEntity> entities){
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

        return new Schedule(
                specialistId,
                workingDays
        );
    }

    public ScheduleDTO scheduleToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getSpecialistId(),
                schedule.getWorkingDays().stream().map(day->new WorkingDayDTO(
                        day.getDayOfWeek(),
                        day.getStart(),
                        day.getEnd()
                )).toList()
        );
    }

    public List<ScheduleJpaEntity> toScheduleEntities(Schedule schedule) {

        return schedule.getWorkingDays().stream()
                .map(day -> {
                    ScheduleJpaEntity e = new ScheduleJpaEntity(
                            schedule.getSpecialistId(),
                            day.getDayOfWeek(),
                            day.getStart(),
                            day.getEnd()
                    );
                    return e;
                })
                .toList();
    }



}