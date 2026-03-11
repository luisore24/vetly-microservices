package com.company.microservice_auth.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Observed {

    String event();
    boolean logRequest() default true;
    boolean logResponse() default true;

}
