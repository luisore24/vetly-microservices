package com.company.microservice_catalog.application.port.in.offeredservice;

import com.company.microservice_catalog.domain.model.OfferedService;
import com.company.microservice_catalog.domain.model.Product;

public interface UpdateOfferedServiceUseCase {

    OfferedService execute(Long id, OfferedService offeredService);

}
