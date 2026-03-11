package com.company.microservice_catalog.application.port.in.offeredservice;

import com.company.microservice_catalog.domain.model.OfferedService;

public interface CreateOfferedServiceUseCase {

    OfferedService execute(OfferedService offeredService);

}
