package com.company.microservice_auth.config.filter;

import io.micrometer.tracing.Tracer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceFilter implements Filter {


    private final Tracer tracer;

    //private final Propagator propagator;

    public TraceFilter(Tracer tracer) {
        this.tracer = tracer;
        //this.propagator = propagator;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var currentSpan = this.tracer.currentSpan();
        if (currentSpan != null) {
            log.info("DoFilter TraceFilter: {}", currentSpan.context().traceId());
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            // Agregamos el traceId al header de salida
            httpServletResponse.setHeader("X-Trace-Id", currentSpan.context().traceId());
        }

        filterChain.doFilter(servletRequest, servletResponse);


//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//
//        var currentSpan = tracer.currentSpan();
//
//        if (currentSpan != null) {
//
//            log.info("DoFilter TraceFilter: {}", currentSpan.context().traceId());
//
//            String traceId = currentSpan.context().traceId();
//            httpServletResponse.setHeader("X-Trace-Id", traceId);
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);


    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        Propagator.Getter<HttpServletRequest> getter =
//                HttpServletRequest::getHeader;
//
//        Span span = propagator
//                .extract(request, getter)
//                .start();
//
//        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {
//            filterChain.doFilter(request, response);
//        } finally {
//            span.end();
//        }
//
//    }
}
