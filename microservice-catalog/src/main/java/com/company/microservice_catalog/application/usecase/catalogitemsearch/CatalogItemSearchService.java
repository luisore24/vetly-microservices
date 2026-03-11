package com.company.microservice_catalog.application.usecase.catalogitemsearch;

import com.company.microservice_catalog.application.port.in.catalogitemsearch.CatalogItemSearchUseCase;
import com.company.microservice_catalog.application.port.out.CatalogItemSearchRepositoryPort;
import com.company.microservice_catalog.domain.model.CatalogItemSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogItemSearchService implements CatalogItemSearchUseCase {

    private final CatalogItemSearchRepositoryPort catalogItemSearchRepository;

    @Override
    public List<CatalogItemSearch> execute(String name) {

        if(name == null || name.isBlank()){
            return List.of();
        }
        return catalogItemSearchRepository.searchByName(name.trim());
    }

}
