package com.company.microservice_auth.service;

import com.company.microservice_auth.common.ApiResponse;
import java.util.List;

public interface CommonService<C,U, R> {

    /*
    * THIS SERVICE IN ONLY FOR EXPOSE SERVICE TO CONTROLLER
    * -- DETAILS OF GENERICS
    * C -> TYPE FOR CREATE ENTITY
    * U -> TYPE FOR UPDATE ENTITY
    * R -> TYPE FOR RESPONSE ENTITY
    * T -> TYPE FOR FIND OR REMOVE REGISTER (INTEGER OR LONG OR OTHER SIMILAR)
    */

    ApiResponse<R> register(C entity);
    ApiResponse<R> update(U entity);
    ApiResponse<R> findById(Long id);
    ApiResponse<List<R>> findAll();
    ApiResponse<Void> remove(Long id);

}
