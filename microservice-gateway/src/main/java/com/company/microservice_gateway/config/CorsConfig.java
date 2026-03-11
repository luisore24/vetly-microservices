package com.company.microservice_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.List;

@Configuration
public class CorsConfig {


    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // Permite el origen de tu React (Vite por defecto usa 5173)
        corsConfig.addAllowedOrigin("*");

        // Permite todos los métodos (GET, POST, PUT, DELETE, etc.)
        corsConfig.addAllowedMethod("*");

        // Permite todos los headers
        corsConfig.addAllowedHeader("*");

        // Permite el envío de credenciales (cookies, auth headers) si fuera necesario
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuración a todas las rutas del gateway
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);

    }
}
