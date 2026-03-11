package com.company.microservice_catalog.application.port.out;

import com.company.microservice_catalog.domain.model.CatalogItemSearch;

import java.util.List;

public interface CatalogItemSearchRepositoryPort {

    List<CatalogItemSearch> searchByName(String name);

}
