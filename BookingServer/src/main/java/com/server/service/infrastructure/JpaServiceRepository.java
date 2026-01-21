package com.server.service.infrastructure;

import com.server.service.domain.Service;
import com.server.service.domain.ServiceRepository;
import com.server.shared.infrastructure.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaServiceRepository implements ServiceRepository {

    private final ServiceJpaRepository serviceJpaRepository;
    private final UserMapper mapper;

    public JpaServiceRepository(ServiceJpaRepository serviceJpaRepository, UserMapper mapper) {
        this.serviceJpaRepository = serviceJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Service> findById(int id) {
        return serviceJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Service> findAll() {
        return serviceJpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Service save(Service service) {
        ServiceJpaEntity entity = mapper.toEntity(service);
        return mapper.toDomain(serviceJpaRepository.save(entity));
    }

    @Override
    public void delete(Service service) {
        serviceJpaRepository.deleteById(service.getId());
    }
}
