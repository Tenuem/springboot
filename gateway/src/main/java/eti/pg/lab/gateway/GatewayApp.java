package eti.pg.lab.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("authors", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/authors", "/api/authors/{id}")
                        .uri("http://localhost:8081")
                )
                .route("songs", r->r
                        .host("localhost:8080")
                        .and()
                        .path("/api/songs", "/api/songs/{id}", "/api/authors/{id}/songs", "/api/authors/{id}/songs/{songId}")
                        .uri("http://localhost:8082")
                )
                .route("covers", r->r
                        .host("localhost:8080")
                        .and()
                        .path("/api/covers", "/api/covers/{id}")
                        .uri("http://localhost:8083")
                )
                .build();

    }

    @Bean
    public CorsWebFilter corsWebFilter() {


        CorsConfiguration corsConfig = new CorsConfiguration();

        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);

    }


}
