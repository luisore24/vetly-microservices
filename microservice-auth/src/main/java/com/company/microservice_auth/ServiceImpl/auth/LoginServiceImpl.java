package com.company.microservice_auth.ServiceImpl.auth;

import com.company.microservice_auth.dto.auth.AuditLoginRequestDTO;
import com.company.microservice_auth.dto.auth.LoginRequest;
import com.company.microservice_auth.dto.auth.LoginResponse;
import com.company.microservice_auth.service.auth.AuditLoginService;
import com.company.microservice_auth.service.auth.LoginService;
import com.company.microservice_auth.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AuditLoginService auditLoginService;

    @Override
    public LoginResponse loginAuthenticate(LoginRequest request, HttpServletRequest httpServletRequest) {

        Authentication authentication = this.AuthenticateCredentials(request.getUsername(), request.getPassword(), httpServletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuditLoginRequestDTO auditLoginRequestDTO = AuditLoginRequestDTO.builder()
                .ipAddress((httpServletRequest.getHeader("X-Forwarded-For") != null && !httpServletRequest.getHeader("X-Forwarded-For").isEmpty()) ? httpServletRequest.getRemoteAddr() : "unknown")
                .createdAt(LocalDateTime.now())
                .isSuccessful(true)
                .username(request.getUsername())
                .message(messageSource.getMessage("login.successful", null, Locale.getDefault()))
                .userAgent(httpServletRequest.getHeader("User-Agent"))
                .build();

        System.out.println(auditLoginRequestDTO.toString());
        auditLoginService.register(auditLoginRequestDTO);

        String accessToken = jwtUtil.generateToken(authentication);

        LoginResponse response = new LoginResponse(request.getUsername(), accessToken, "200");

        return response;


    }

    private Authentication AuthenticateCredentials(String username, String password, HttpServletRequest httpServletRequest) {

        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), password);

            return authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            AuditLoginRequestDTO auditLoginRequestDTO = AuditLoginRequestDTO.builder()
                    .ipAddress((httpServletRequest.getHeader("X-Forwarded-For") != null && !httpServletRequest.getHeader("X-Forwarded-For").isEmpty()) ? httpServletRequest.getRemoteAddr() : "unknown")
                    .createdAt(LocalDateTime.now())
                    .isSuccessful(false)
                    .username(username)
                    .message(e.getMessage())
                    .userAgent(httpServletRequest.getHeader("User-Agent"))
                    .build();
            System.out.println(auditLoginRequestDTO.toString());
            auditLoginService.register(auditLoginRequestDTO);

            throw e;
        }


    }


}
