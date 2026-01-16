package com.company.microservice_auth.common;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ErrorResponseFactory {


    public ApiErrorResponse buildFromException(Exception ex, String path, String tracerId, Integer statusCode, String message) {

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message(message)
                .error(ex.getClass().getSimpleName())
                .statusCode(statusCode)
                .path(path)
                .traceId(tracerId)
                .timestamp(LocalDateTime.now())
                .build();


        return apiErrorResponse;

    }


}
