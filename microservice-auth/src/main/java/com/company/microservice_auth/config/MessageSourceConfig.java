package com.company.microservice_auth.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSourceConfig = new ReloadableResourceBundleMessageSource();
        messageSourceConfig.setBasenames("classpath:messages");
        messageSourceConfig.setDefaultEncoding("UTF-8");
        return messageSourceConfig;
    }

}
