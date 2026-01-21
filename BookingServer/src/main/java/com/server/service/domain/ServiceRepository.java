package com.server.service.domain;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {

    Optional<Service> findById(int id);

    List<Service> findAll();

    Service save(Service service);

    void delete(Service service);
}
