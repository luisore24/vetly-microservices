package com.company.microservice_auth.exception;

import com.company.microservice_auth.config.MessageSourceConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

@Component
public class CustomAuthenticationExceptionHandler implements AuthenticationFailureHandler {


    @Autowired
    private MessageSource messageSource;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String message = "", statusCode = "", error = "", path = "";
        String responseErrorJson = "";

        switch (exception) {
            case BadCredentialsException badCredentialsException -> {
                message = messageSource.getMessage("error.badcredential.message", null, Locale.getDefault());
                statusCode = messageSource.getMessage("error.badcredential.statuscode", null, Locale.getDefault());
                error = messageSource.getMessage("error.badcredential.error", null, Locale.getDefault());
            }
            case LockedException lockedException -> {
                message = messageSource.getMessage("error.accountlocked.message", null, Locale.getDefault());
                statusCode = messageSource.getMessage("error.accountlocked.statuscode", null, Locale.getDefault());
                error = messageSource.getMessage("error.locked.error", null, Locale.getDefault());
            }
            case AccountExpiredException accountExpiredException ->{
                message = messageSource.getMessage("error.accountexpired.message", null, Locale.getDefault());
                statusCode = messageSource.getMessage("error.accountexpired.statuscode", null, Locale.getDefault());
                error = messageSource.getMessage("error.accountexpired.error", null, Locale.getDefault());
            }
            case CredentialsExpiredException credentialsExpiredException ->{
                message = messageSource.getMessage("error.credentialexpired.message", null, Locale.getDefault());
                statusCode = messageSource.getMessage("error.credentialexpired.statuscode", null, Locale.getDefault());
                error = messageSource.getMessage("error.credentialexpired.error", null, Locale.getDefault());
            }
            case DisabledException disabledException -> {
                message = messageSource.getMessage("error.accountdisable.message", null, Locale.getDefault());
                statusCode = messageSource.getMessage("error.accountdisable.statuscode", null, Locale.getDefault());
                error = messageSource.getMessage("error.accountdisable.error", null, Locale.getDefault());
            }
            case null, default -> {
                message = messageSource.getMessage("error.logindefault.message", null, Locale.getDefault());
                statusCode = messageSource.getMessage("error.logindefault.statuscode", null, Locale.getDefault());
                error = messageSource.getMessage("error.logindefault.error", null, Locale.getDefault());
            }

        }

        responseErrorJson = responseError(message,statusCode, error, LocalDateTime.now(), request.getRequestURI());

        response.setContentType("application/json");
        response.getWriter().write(responseErrorJson);
    }

    private String responseError(String message, String statusCode, String error, LocalDateTime time, String path){
        return String.format(
                "{\"Message\": \"%s\", \"StatusCode:\" \"%s\", \"Error:\" \"%s\", \"timestamp:\" \"%s\", \"Path:\" \"%s\"}",
                message, statusCode, error, time, path);
    }
}
