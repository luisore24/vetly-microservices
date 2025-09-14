package com.company.microservice_auth.service.ServiceImpl.auth;

import com.company.microservice_auth.dto.auth.LoginRequest;
import com.company.microservice_auth.dto.auth.LoginResponse;
import com.company.microservice_auth.service.auth.LoginService;
import com.company.microservice_auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

    @Override
    public LoginResponse loginAuthenticate(LoginRequest request) {

        Authentication authentication = this.AuthenticateCredentials(request.getUsername(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtil.generateToken(authentication);

        LoginResponse response = new LoginResponse(request.getUsername(), accessToken, "200");

        return response;
    }

    private Authentication AuthenticateCredentials(String username, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        Authentication authentication =  new UsernamePasswordAuthenticationToken(username,password);

        return authenticationManager.authenticate(authentication);
    }


}
