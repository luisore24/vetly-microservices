package com.company.microservice_catalog.application.port.in.offeredservice;

import com.company.microservice_catalog.domain.model.OfferedService;

import java.util.List;
import java.util.Optional;

public interface GetOfferedServiceUseCase {

    Optional<OfferedService> getService(Long id);

    List<OfferedService> getAllServices();

}
