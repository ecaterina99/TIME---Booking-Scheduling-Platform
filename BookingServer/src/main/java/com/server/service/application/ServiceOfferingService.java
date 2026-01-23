package com.server.service.application;

import com.server.service.api.ServiceDTO;
import com.server.service.domain.ServiceDuration;
import com.server.service.domain.ServiceName;
import com.server.service.domain.ServicePrice;
import com.server.service.domain.ServiceRepository;
import com.server.service.infrastructure.ServiceMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOfferingService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceOfferingService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Transactional(readOnly = true)
    public ServiceDTO getServiceById(int id) {
        com.server.service.domain.Service service = findServiceById(id);
        return serviceMapper.toDTO(service);
    }

    @Transactional(readOnly = true)
    public List<ServiceDTO> getAllServices() {
        List<com.server.service.domain.Service> services = serviceRepository.findAll();
        return services.stream().map(serviceMapper::toDTO).toList();
    }

    @Transactional
    public int addService(AddServiceCommand command) {
        com.server.service.domain.Service service = new com.server.service.domain.Service(
                0,
                new ServiceName(command.name()),
                command.organizationId(),
                command.description(),
                new ServiceDuration(command.durationMinutes()),
                new ServicePrice(command.price())
        );
        return serviceRepository.save(service).getId();
    }

    public void updateService(UpdateServiceCommand command) {
        com.server.service.domain.Service service = findServiceById(command.id());

        if (command.name() != null) {
            service.changeName(new ServiceName(command.name()));
        }
        if (command.description() != null && !command.description().isEmpty()) {
            service.changeDescription(command.description());
        }
        if (command.organizationId() != null) {
            service.changeOrganizationId(command.organizationId());
        }
        if (command.durationMinutes() != null) {
            service.changeDuration(new ServiceDuration(command.durationMinutes()));
        }
        if (command.price() != null) {
            service.changePrice(new ServicePrice(command.price()));
        }
        serviceRepository.save(service);
    }

    @Transactional
    public void deleteServiceById(int id) {
        com.server.service.domain.Service service = findServiceById(id);
        serviceRepository.delete(service);
    }

    private com.server.service.domain.Service findServiceById(int id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Service with id: " + id + " is not found"
                ));
    }
}
