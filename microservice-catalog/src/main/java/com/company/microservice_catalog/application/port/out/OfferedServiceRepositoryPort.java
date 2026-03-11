package com.company.microservice_catalog.application.port.out;

import com.company.microservice_catalog.domain.model.OfferedService;

import java.util.List;
import java.util.Optional;

public interface OfferedServiceRepositoryPort {


    OfferedService saveService(OfferedService offeredService);

    OfferedService updateService(Long id, OfferedService offeredService);

    Optional<OfferedService> findService(Long id);

    List<OfferedService> findAllServices();

    void deleteService(Long id);


}
