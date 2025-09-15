package com.company.microservice_auth.config.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.company.microservice_auth.exception.CustomAuthenticationEntryPointExceptionHandler;
import com.company.microservice_auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    private AuthenticationEntryPoint authenticationEntryPoint;

    private JwtUtil jwtUtil;

    public JwtTokenValidator(JwtUtil jwtUtil, AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {



        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken != null && !jwtToken.isEmpty() && !jwtToken.isBlank()){

            jwtToken = jwtToken.substring(7);

            try {

                DecodedJWT decodedJWT = jwtUtil.verifyToken(jwtToken);

                String username = jwtUtil.getUsername(decodedJWT);
//            String authoritiesToken = jwtUtil.getClaim(decodedJWT, "authorities").asString();
                List<String> authoritiesToken = jwtUtil.getClaim(decodedJWT, "authorities").asList(String.class);

                //Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesToken);
                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authoritiesToken);

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);
            }
            catch (JWTVerificationException exception){
                SecurityContextHolder.clearContext();
                System.out.println(exception.getMessage());
                authenticationEntryPoint.commence(request, response, new InternalAuthenticationServiceException(exception.getMessage() + exception));
                return;
            }
        }

        filterChain.doFilter(request, response);

    }
}
