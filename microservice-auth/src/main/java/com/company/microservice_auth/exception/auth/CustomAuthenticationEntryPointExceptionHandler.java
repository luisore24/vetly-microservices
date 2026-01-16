package com.company.microservice_auth.exception.auth;

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
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {


//        String message = "",  error = "";
//
//        Integer statusCode = 0;

        String traceId = "N/A";
        if (tracer.currentSpan() != null) {
            traceId = tracer.currentSpan().context().traceId();
        }




//        if (authException instanceof UsernameNotFoundException) {
//            message = getMessageProperties("error.usernamenotfound.message");
//            statusCode = Integer.parseInt(getMessageProperties("error.usernamenotfound.statuscode"));
//            error = getMessageProperties("error.usernamenotfound.error");
//        } else if (authException instanceof BadCredentialsException) {
//            message = getMessageProperties("error.badcredential.message");
//            statusCode = Integer.parseInt(getMessageProperties("error.badcredential.statuscode"));
//            error = getMessageProperties("error.badcredential.error");
//        } else if (authException instanceof DisabledException) {
//            message = getMessageProperties("error.accountdisable.message");
//            statusCode = Integer.parseInt(getMessageProperties("error.accountdisable.statuscode"));
//            error = getMessageProperties("error.accountdisable.error");
//        } else if (authException instanceof LockedException) {
//            message = getMessageProperties("error.accountlocked.message");
//            statusCode = Integer.parseInt(getMessageProperties("error.accountlocked.statuscode"));
//            error = getMessageProperties("error.locked.error");
//        } else if (authException instanceof AccountExpiredException) {
//            message = getMessageProperties("error.accountexpired.message");
//            statusCode = Integer.parseInt(getMessageProperties("error.accountexpired.statuscode"));
//            error = getMessageProperties("error.accountexpired.error");
//        } else if (authException instanceof CredentialsExpiredException) {
//            message = getMessageProperties("error.credentialexpired.message");
//            statusCode = Integer.parseInt(getMessageProperties("error.credentialexpired.statuscode"));
//            error = getMessageProperties("error.credentialexpired.error");
//        }
//        else {
//            message = getMessageProperties("error.logindefault.message");
//            statusCode = Integer.parseInt(getMessageProperties("error.logindefault.statuscode"));
//            error = authException.getMessage();
//        }

        System.out.println("commence error");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ApiErrorResponse apiErrorResponse = errorResponseFactory.buildFromException(authException, request.getRequestURI(), traceId, HttpStatus.UNAUTHORIZED.value(), getMessageProperties("error.logindefault.message"));

        String reponseJson = objectMapper.writeValueAsString(apiErrorResponse);


        response.setContentType("application/json");
        response.setStatus(apiErrorResponse.getStatusCode());
        response.getWriter().write(reponseJson);

    }

    private String getMessageProperties(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

}
