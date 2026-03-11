package com.company.microservice_catalog.application.port.in.catalogitemsearch;

import com.company.microservice_catalog.domain.model.CatalogItemSearch;
import java.util.List;

public interface CatalogItemSearchUseCase {

    List<CatalogItemSearch> execute(String name);

}
