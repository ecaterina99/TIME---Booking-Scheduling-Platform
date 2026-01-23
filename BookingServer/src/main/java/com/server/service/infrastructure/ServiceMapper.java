package com.server.service.infrastructure;

import com.server.service.api.ServiceDTO;
import com.server.service.domain.Service;
import com.server.service.domain.ServiceDuration;
import com.server.service.domain.ServiceName;
import com.server.service.domain.ServicePrice;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public Service toDomain(ServiceJpaEntity entity) {
        return new Service(
                entity.getId(),
                new ServiceName(entity.getName()),
                entity.getOrganizationId(),
                entity.getDescription(),
                new ServiceDuration(entity.getDuration()),
                new ServicePrice(entity.getPrice())
        );
    }

    public ServiceJpaEntity toEntity(Service service) {
        ServiceJpaEntity entity = new ServiceJpaEntity(
                service.getName().value(),
                service.getOrganizationId(),
                service.getDescription(),
                service.getDurationMinutes().minutes(),
                service.getPrice().price()
        );
        entity.setId(service.getId());
        return entity;
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
}
