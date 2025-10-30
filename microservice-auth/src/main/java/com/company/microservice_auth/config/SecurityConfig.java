package com.company.microservice_auth.config;

import com.company.microservice_auth.config.filter.JwtTokenValidator;
import com.company.microservice_auth.exception.auth.CustomAuthenticationEntryPointExceptionHandler;
import com.company.microservice_auth.ServiceImpl.auth.CustomUserDetailsService;
import com.company.microservice_auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAuthenticationEntryPointExceptionHandler customAuthenticationEntryPointExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                //.httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(https -> {
                    https.requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll();
                    https.requestMatchers("/api/v1/auth/validate").authenticated();
                    https.requestMatchers("/api/v1/users/**").hasRole("ADMIN");
                    https.requestMatchers("/api/v1/roles/**").hasRole("ADMIN");
                    https.requestMatchers("/api/v1/permissions/**").hasRole("ADMIN");
                    https.requestMatchers("/api/v1/menus/**").hasRole("ADMIN");
                    https.requestMatchers("/api/v1/status/**").hasRole("ADMIN");
                    //https.requestMatchers(HttpMethod.GET, "/v1/auth/login").hasRole("ADMIN");
                    https.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtil, customAuthenticationEntryPointExceptionHandler), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        exception -> {
                            exception.authenticationEntryPoint(customAuthenticationEntryPointExceptionHandler);
                            //exception.accessDeniedHandler(customAuthenticationEntryPointExceptionHandler);
                        }
                )
                .build();

        //
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("admin"));
//    }

}
