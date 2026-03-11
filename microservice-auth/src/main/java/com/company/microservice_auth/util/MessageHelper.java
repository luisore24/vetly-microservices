package com.company.microservice_auth.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;


    public String getMessage(String code){
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public String getMessage(String code, Object...args){
        return messageSource.getMessage(code, args, Locale.getDefault());
    }


}
