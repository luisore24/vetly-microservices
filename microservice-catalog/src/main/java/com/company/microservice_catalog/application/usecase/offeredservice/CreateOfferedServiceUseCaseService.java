package com.company.microservice_catalog.application.usecase.offeredservice;

import com.company.microservice_catalog.application.port.in.offeredservice.CreateOfferedServiceUseCase;
import com.company.microservice_catalog.application.port.out.OfferedServiceRepositoryPort;
import com.company.microservice_catalog.domain.model.OfferedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CreateOfferedServiceUseCaseService implements CreateOfferedServiceUseCase {

    private final OfferedServiceRepositoryPort offeredServiceRepositoryPort;


    @Override
    @Transactional
    public OfferedService execute(OfferedService offeredService) {
        return offeredServiceRepositoryPort.saveService(offeredService);
    }
}
