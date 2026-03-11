package com.company.microservice_catalog.application.usecase.offeredservice;

import com.company.microservice_catalog.application.port.in.offeredservice.DeleteOfferedServiceUseCase;
import com.company.microservice_catalog.application.port.out.OfferedServiceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteOfferedServiceUseCaseService implements DeleteOfferedServiceUseCase {

    private final OfferedServiceRepositoryPort offeredServiceRepositoryPort;

    @Override
    public void execute(Long id) {
        offeredServiceRepositoryPort.deleteService(id);
    }
}
