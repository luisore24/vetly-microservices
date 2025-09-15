package com.company.microservice_auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${security.jwt.private.key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    @Value("${security.jwt.token.timelife}")
    private Long tokenTimeLife;


    public String generateToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC512(this.privateKey);

        //String username = authentication.getPrincipal().toString();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                //.collect(Collectors.joining(","));
                .collect(Collectors.toList());



        String jwtToken = JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenTimeLife))
                //.withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return jwtToken;
    }

    public DecodedJWT verifyToken(String token){
        try {

            JWTVerifier jwtVerifier = getJWTVerifier();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT;

        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid token: " + exception.getMessage());
        }
    }

    private JWTVerifier getJWTVerifier(){

        Algorithm algorithm = Algorithm.HMAC512(this.privateKey);
        return JWT.require(algorithm)
                .withIssuer(this.userGenerator)
                .build();
    }

    public String getUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getClaim(DecodedJWT decodedJWT, String claim){
        return decodedJWT.getClaim(claim);
    }

}
