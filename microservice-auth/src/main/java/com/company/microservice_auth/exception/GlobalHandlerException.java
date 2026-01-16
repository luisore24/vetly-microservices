package com.company.microservice_auth.exception;

import com.company.microservice_auth.common.ApiErrorResponse;
import com.company.microservice_auth.common.ErrorResponseFactory;
import com.company.microservice_auth.exception.common.ResourceAlreadyExistException;
import com.company.microservice_auth.exception.common.ResourceNotFoundException;
import com.company.microservice_auth.exception.common.ResourceNullException;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalHandlerException {

    @Autowired
    private Tracer tracer;

    @Autowired
    private ErrorResponseFactory errorResponseFactory;

    @Autowired
    private MessageSource messageSource;

    private String getTracerId(){
        if(tracer.currentSpan() != null){
            return tracer.currentSpan().context().traceId();
        }
        return "N/A";
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        return new ResponseEntity<>(errorResponseFactory.buildFromException(ex, request.getRequestURI(), getTracerId(), HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handlerAlreadyExist(ResourceAlreadyExistException ex, HttpServletRequest request){
        return new ResponseEntity<>(errorResponseFactory.buildFromException(ex, request.getRequestURI(), getTracerId(), HttpStatus.CONFLICT.value(), ex.getMessage()), HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(ResourceNullException.class)
    public ResponseEntity<ApiErrorResponse> handlerNullException(ResourceNullException ex, HttpServletRequest request){
        return new ResponseEntity<>(errorResponseFactory.buildFromException(ex, request.getRequestURI(), getTracerId(), HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handlerAccessDeniedException(AccessDeniedException ex, HttpServletRequest request){
        return new ResponseEntity<>(errorResponseFactory.buildFromException(ex, request.getRequestURI(), getTracerId(), HttpStatus.FORBIDDEN.value(), ex.getMessage()), HttpStatusCode.valueOf(403));
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handlerAuthenticationException(AuthenticationException ex, HttpServletRequest request){
        return new ResponseEntity<>(errorResponseFactory.buildFromException(ex, request.getRequestURI(), getTracerId(), HttpStatus.UNAUTHORIZED.value(), getMessageProperties("error.badcredential.message")),HttpStatusCode.valueOf(401));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handlerGeneral(Exception ex, HttpServletRequest request){
        return new ResponseEntity<>(errorResponseFactory.buildFromException(ex, request.getRequestURI(), getTracerId(), HttpStatus.INTERNAL_SERVER_ERROR.value(), getMessageProperties("error.internal.message")), HttpStatusCode.valueOf(500));
    }


    private String getMessageProperties(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

}
