package com.company.microservice_auth.exception;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.exception.common.ResourceAlreadyExistException;
import com.company.microservice_auth.exception.common.ResourceNotFoundException;
import com.company.microservice_auth.exception.common.ResourceNullException;
import io.micrometer.tracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @Autowired
    private Tracer tracer;


    private String getTracerId(){
        if(tracer.currentSpan() != null){
            return tracer.currentSpan().context().traceId();
        }
        return "N/A";
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handlerGeneral(Exception ex){

        return new ResponseEntity<>(
                new ApiResponse<>(false, "TraceId: " + getTracerId() + "Error: " + ex.getMessage()),
                HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlerNotFound(Exception ex){
        return new ResponseEntity<>(
                new ApiResponse<>(false, "TraceId: " + getTracerId() + "Error: " + ex.getMessage()),
                HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiResponse<?>> handlerAlreadyExist(Exception ex){
        return new ResponseEntity<>(
                new ApiResponse<>(false, "TraceId: " + getTracerId() + "Error: " + ex.getMessage()),
                HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(ResourceNullException.class)
    public ResponseEntity<ApiResponse<?>> handlerNullException(Exception ex){
        return new ResponseEntity<>(
                new ApiResponse<>(false, "TraceId: " + getTracerId() + "Error: " + ex.getMessage()),
                HttpStatusCode.valueOf(400));
    }


}
