package com.company.microservice_auth.config.filter;


import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceFilter implements Filter {


    private final Tracer tracer;

    public TraceFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 1. Intentamos obtener el span actual
        var currentSpan = tracer.currentSpan();

        // 2. Si no existe, le pedimos a Micrometer que inicie uno nuevo manualmente
        if (currentSpan == null) {
            currentSpan = tracer.nextSpan().name("http-request-filter").start();
        }

        // 3. Ahora usamos el ID de Micrometer (garantizado)
        String traceId = currentSpan.context().traceId();
        httpServletResponse.setHeader("X-Trace-Id", traceId);

        // 4. Metemos todo en un scope (esto llena el MDC automáticamente)
        try (var scope = tracer.withSpan(currentSpan)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // Si nosotros iniciamos el span manualmente, hay que terminarlo
            if (tracer.currentSpan() != null && tracer.currentSpan().equals(currentSpan)) {
                currentSpan.end();
            }
        }


    }
}
