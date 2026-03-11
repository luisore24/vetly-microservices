package com.company.microservice_auth.service;

import java.util.List;

public interface CommonInternalDTOService<R, ID>{

    /*
     * THIS SERVICE IN ONLY FOR COMMUNICATION BETWEEN INTERNAL SERVICE | return entity
     * -- DETAILS OF GENERICS
     * T -> TYPE  ENTITY
     * ID -> ID for find.
     */

    R findEntityDTOById(ID id);
    List<R> findAllEntityDTO();

}
