package com.company.microservice_gateway.common;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorResponse {

    private String message;
    private String error;
    private Integer statusCode;
    private String path;
    private String traceId;
    private LocalDateTime timestamp;


}
