package com.company.microservice_auth.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.company.microservice_auth.dto.auth.AuditLoginRequestDTO;
import com.company.microservice_auth.service.auth.AuditLoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Component
public class CustomAuthenticationEntryPointExceptionHandler implements AuthenticationEntryPoint {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AuditLoginService auditLoginService;



    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String message = "", statusCode = "", error = "", path = "";
        String responseErrorJson = "";

        System.out.println("AuthenticationException class: " + authException.getMessage());


        if (authException instanceof UsernameNotFoundException) {
            message = messageSource.getMessage("error.usernamenotfound.message", null, Locale.getDefault());
            statusCode = messageSource.getMessage("error.usernamenotfound.statuscode", null, Locale.getDefault());
            error = messageSource.getMessage("error.usernamenotfound.error", null, Locale.getDefault());
        } else if (authException instanceof BadCredentialsException){
            message = messageSource.getMessage("error.badcredential.message", null, Locale.getDefault());
            statusCode = messageSource.getMessage("error.badcredential.statuscode", null, Locale.getDefault());
            error = messageSource.getMessage("error.badcredential.error", null, Locale.getDefault());
        }else if (authException instanceof DisabledException) {
            message = messageSource.getMessage("error.accountdisable.message", null, Locale.getDefault());
            statusCode = messageSource.getMessage("error.accountdisable.statuscode", null, Locale.getDefault());
            error = messageSource.getMessage("error.accountdisable.error", null, Locale.getDefault());
        } else if (authException instanceof LockedException) {
            message = messageSource.getMessage("error.accountlocked.message", null, Locale.getDefault());
            statusCode = messageSource.getMessage("error.accountlocked.statuscode", null, Locale.getDefault());
            error = messageSource.getMessage("error.locked.error", null, Locale.getDefault());
        } else if (authException instanceof AccountExpiredException) {
            message = messageSource.getMessage("error.accountexpired.message", null, Locale.getDefault());
            statusCode = messageSource.getMessage("error.accountexpired.statuscode", null, Locale.getDefault());
            error = messageSource.getMessage("error.accountexpired.error", null, Locale.getDefault());
        } else if (authException instanceof CredentialsExpiredException) {
            message = messageSource.getMessage("error.credentialexpired.message", null, Locale.getDefault());
            statusCode = messageSource.getMessage("error.credentialexpired.statuscode", null, Locale.getDefault());
            error = messageSource.getMessage("error.credentialexpired.error", null, Locale.getDefault());
        } else {
            message = messageSource.getMessage("error.logindefault.message", null, Locale.getDefault());
            statusCode = messageSource.getMessage("error.logindefault.statuscode", null, Locale.getDefault());
            error = authException.getMessage();
        }


        responseErrorJson = responseError(message, statusCode, error, LocalDateTime.now(), request.getRequestURI());

        response.setContentType("application/json");
        response.setStatus(Integer.parseInt(statusCode));
        response.getWriter().write(responseErrorJson);

//        AuditLoginRequestDTO auditLoginRequestDTO = AuditLoginRequestDTO.builder()
//                .loginIp((request.getHeader("X-Forwarded-For") != null && !request.getHeader("X-Forwarded-For").isEmpty()) ? request.getRemoteAddr() : "unknown")
//                .createdAt(LocalDateTime.now())
//                .isSuccessful(false)
//                .username(request. != null ? request.getRemoteUser() : "unknown")
//                .userAgent(request.getHeader("User-Agent"))
//                .build();

//        System.out.println(auditLoginRequestDTO.toString());

        //auditLoginService.register(auditLoginRequestDTO);

    }

    private String responseError(String message, String statusCode, String error, LocalDateTime time, String path) {
        return String.format(
                "{\"Message:\" %s, \"StatusCode:\" %s, \"Error:\" %s, \"timestamp:\" %s, \"Path:\" \"%s\"}",
                message, statusCode, error, time, path);
    }

}
