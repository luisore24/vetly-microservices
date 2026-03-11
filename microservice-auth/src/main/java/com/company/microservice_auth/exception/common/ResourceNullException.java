package com.company.microservice_auth.exception.common;

public class ResourceNullException extends RuntimeException{

    public ResourceNullException(String message) {
        super(message);
    }
}
