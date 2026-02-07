package com.company.microservice_gateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.company.microservice_gateway.common.ApiErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class JwtAuthenticationFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private Tracer tracer;

    @Value("${security.jwt.private.key}")
    private String SECRET_KEY;

    @Autowired
    private SecurityProperties securityProperties;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("Filter gateway");

        String path = exchange.getRequest().getPath().toString();

        if(isPublicPath(path)){
           return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return onError(exchange, HttpStatus.UNAUTHORIZED);
        }

        log.info("pass autheader");

        String token = authHeader.substring(7);

        try{
            Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
            JWTVerifier verfier = JWT.require(algorithm).build();

            log.info("valida token");

            DecodedJWT decodedJWT = verfier.verify(token);

            log.info("pass valid token");

            Authentication authentication = new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(),null,List.of());

            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));

        }
        catch (JWTVerificationException exception){
            log.warn("Invalid token: {}", exception.getMessage());
            return onError(exchange, HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isPublicPath(String path) {
        log.info("Public paths: {}", securityProperties.getPublicPaths());
        return  securityProperties.getPublicPaths().
                stream().
                anyMatch(p -> pathMatcher.match(p, path));

    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(status.getReasonPhrase())
                .message("UNAUTHORIZED")
                .path(exchange.getRequest().getPath().value())
                .statusCode(status.value())
                .traceId(getTraceId())
                .build();

        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(apiErrorResponse);
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }

        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }

    private String getTraceId(){
        if (tracer != null && tracer.currentSpan() != null) {
            return tracer.currentSpan().context().traceId();
        }
        return "N/A";
    }
}
