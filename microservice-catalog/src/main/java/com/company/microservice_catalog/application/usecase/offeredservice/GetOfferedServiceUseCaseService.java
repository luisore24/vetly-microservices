package com.company.microservice_catalog.application.usecase.offeredservice;

import com.company.microservice_catalog.application.port.in.offeredservice.GetOfferedServiceUseCase;
import com.company.microservice_catalog.application.port.out.OfferedServiceRepositoryPort;
import com.company.microservice_catalog.domain.model.OfferedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetOfferedServiceUseCaseService implements GetOfferedServiceUseCase {

    private final OfferedServiceRepositoryPort offeredServiceRepositoryPort;

    @Override
    public Optional<OfferedService> getService(Long id) {
        return offeredServiceRepositoryPort.findService(id);
    }

    @Override
    public List<OfferedService> getAllServices() {
        return offeredServiceRepositoryPort.findAllServices();
    }
}
