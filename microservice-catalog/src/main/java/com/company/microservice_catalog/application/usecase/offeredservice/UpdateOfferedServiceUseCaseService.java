package com.company.microservice_catalog.application.usecase.offeredservice;

import com.company.microservice_catalog.application.port.in.offeredservice.UpdateOfferedServiceUseCase;
import com.company.microservice_catalog.application.port.out.OfferedServiceRepositoryPort;
import com.company.microservice_catalog.domain.model.OfferedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOfferedServiceUseCaseService implements UpdateOfferedServiceUseCase {

    private final OfferedServiceRepositoryPort offeredServiceRepositoryPort;

    @Override
    @Transactional
    public OfferedService execute(Long id, OfferedService offeredService) {
        return offeredServiceRepositoryPort.updateService(id, offeredService);
    }
}
