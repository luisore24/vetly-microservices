package com.company.microservice_gateway.filter;

import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


//GlobalFilter, Ordered
@Component
@Slf4j
public class TraceIdGlobalFilter implements WebFilter {

    public static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Autowired
    private Tracer tracer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        exchange.getResponse().beforeCommit(() -> {
            var span = tracer.currentSpan();
            if (span != null) {
                exchange.getResponse().getHeaders()
                        .add("X-Gateway-Trace-Id", span.context().traceId());
            }
            return Mono.empty();
        });
        return chain.filter(exchange);
    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        return chain.filter(exchange)
//                .doOnSuccess(v -> {
//                    if (tracer.currentSpan() != null) {
//
//                        String traceId = tracer.currentSpan().context().traceId();
//
//                        log.info("Incomming Request | traceId={} | path={}", traceId, exchange.getRequest().getPath().value());
//
//                        exchange.getResponse()
//                                .getHeaders()
//                                .add(TRACE_ID_HEADER, traceId);
//                    }
//                });
//    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        ServerHttpRequest request = exchange.getRequest();
//
//        String traceparent = request.getHeaders().getFirst("traceparent");
//
//        if (traceparent != null) {
//            log.info("Incomming Request | traceId={} | path={}", traceparent, exchange.getRequest().getPath().value());
//            exchange = exchange.mutate()
//                    .request(
//                            request.mutate()
//                                    .header("traceparent", traceparent)
//                                    .build()
//                    )
//                    .build();
//        }
//
//        return chain.filter(exchange);
//    }

//    @Override
//    public int getOrder() {
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        return chain.filter(exchange)
//                .doOnSuccess(v -> logTrace(exchange))
//                .doOnError(e -> logTrace(exchange));
//    }
//
//    private void logTrace(ServerWebExchange exchange) {
//        String traceId = tracer.currentSpan() != null
//                ? tracer.currentSpan().context().traceId()
//                : "N/A";
//
//        log.info("Gateway Request | traceId={} | path={}",
//                traceId,
//                exchange.getRequest().getPath().value());
//    }
//
//    @Override
//    public int getOrder() {
//        return Ordered.LOWEST_PRECEDENCE;
//    }
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        return chain.filter(exchange)
//                .doFirst(() -> {
//                    String traceId = tracer.currentSpan() != null
//                            ? tracer.currentSpan().context().traceId()
//                            : "N/A";
//                    log.info("Incomming Request | traceId={} | path={}", traceId, exchange.getRequest().getPath().value());
//                });
//    }
//
//    @Override
//    public int getOrder() {
//        return -1; // antes de seguridad
//    }
}
