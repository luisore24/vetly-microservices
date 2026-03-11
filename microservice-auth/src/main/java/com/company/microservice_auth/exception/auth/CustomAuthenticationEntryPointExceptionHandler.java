package com.company.microservice_auth.exception.auth;

import com.company.microservice_auth.aspect.Observed;
import com.company.microservice_auth.common.ApiErrorResponse;
import com.company.microservice_auth.common.ErrorResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

@Component
public class CustomAuthenticationEntryPointExceptionHandler implements AuthenticationEntryPoint {

    @Autowired
    private ErrorResponseFactory errorResponseFactory;

    @Autowired
    private Tracer tracer;

    @Autowired
    private MessageSource messageSource;

    private String getTracerId(){
        if(tracer.currentSpan() != null){
            return tracer.currentSpan().context().traceId();
        }
        return "N/A";
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ApiErrorResponse apiErrorResponse = getApiErrorResponseObserved(authException, request,getTracerId());

        String reponseJson = objectMapper.writeValueAsString(apiErrorResponse);


        response.setContentType("application/json");
        response.setStatus(apiErrorResponse.getStatusCode());
        response.getWriter().write(reponseJson);

    }


    @Observed(event = "AUTH_UNAUTHORIZED", logRequest = false)
    private ApiErrorResponse getApiErrorResponseObserved(AuthenticationException authenticationException, HttpServletRequest request, String traceId){
        return errorResponseFactory.buildFromException(authenticationException, request.getRequestURI(), traceId, HttpStatus.UNAUTHORIZED.value(), getMessageProperties("error.logindefault.message"));
    }

    private String getMessageProperties(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

}
