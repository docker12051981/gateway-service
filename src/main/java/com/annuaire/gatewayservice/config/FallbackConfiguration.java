package com.annuaire.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

/**
 * @author karim hmadi

 * @description component fallback

 */

@Configuration
public class FallbackConfiguration {
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/annuaire-fallback"),
                        this::handleGetFallback)
                .andRoute(RequestPredicates.POST("/annuaire-fallback"),
                        this::handlePostFallback);
    }

    public Mono<ServerResponse> handleGetFallback(ServerRequest request) {
        return ServerResponse.ok().body(Mono.empty(), String.class);
    }

    public Mono<ServerResponse> handlePostFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
