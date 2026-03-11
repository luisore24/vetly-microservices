package com.company.microservice_catalog.infrastructure.adapter.in.web;


import com.company.microservice_catalog.application.port.in.offeredservice.CreateOfferedServiceUseCase;
import com.company.microservice_catalog.application.port.in.offeredservice.DeleteOfferedServiceUseCase;
import com.company.microservice_catalog.application.port.in.offeredservice.GetOfferedServiceUseCase;
import com.company.microservice_catalog.application.port.in.offeredservice.UpdateOfferedServiceUseCase;
import com.company.microservice_catalog.domain.model.OfferedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/service")
@RequiredArgsConstructor
public class ServiceController {

    private final CreateOfferedServiceUseCase createServiceUseCase;
    private final UpdateOfferedServiceUseCase updateServiceUseCase;
    private final GetOfferedServiceUseCase getServiceUseCase;
    private final DeleteOfferedServiceUseCase deleteServiceUseCase;


    @PostMapping("/create")
    public ResponseEntity<OfferedService> create(@RequestBody OfferedService service){

        OfferedService createdService = createServiceUseCase.execute(service);

        return new ResponseEntity<>(createdService, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OfferedService> update(@PathVariable("id") Long id, @RequestBody OfferedService service){

        OfferedService updatedService = updateServiceUseCase.execute(id, service);

        return new ResponseEntity<>(updatedService, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferedService> getService(@PathVariable("id") Long id){

        Optional<OfferedService> serviceFound = getServiceUseCase.getService(id);

        return new ResponseEntity<>(serviceFound.get(), HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<OfferedService>> getAllService(){

        List<OfferedService> getServices = getServiceUseCase.getAllServices();

        return new ResponseEntity<>(getServices, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){

        deleteServiceUseCase.execute(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
