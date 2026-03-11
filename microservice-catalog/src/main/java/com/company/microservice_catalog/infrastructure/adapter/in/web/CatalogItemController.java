package com.company.microservice_catalog.infrastructure.adapter.in.web;

import com.company.microservice_catalog.application.port.in.catalogitemsearch.CatalogItemSearchUseCase;
import com.company.microservice_catalog.domain.model.CatalogItemSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/v1/catalog")
@RequiredArgsConstructor
public class CatalogItemController {

    private final CatalogItemSearchUseCase catalogItemSearchUseCase;


    @GetMapping("/search")
    public ResponseEntity<List<CatalogItemSearch>> search(@RequestParam String text){
        return new ResponseEntity<>(catalogItemSearchUseCase.execute(text), HttpStatusCode.valueOf(200));
    }

}
