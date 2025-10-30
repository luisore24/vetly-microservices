package com.company.microservice_auth.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuditHelper {

    public String getUserLogged(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public LocalDateTime getTimeNow(){
        return LocalDateTime.now();
    }

}
