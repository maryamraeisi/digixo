package ir.digixo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/api/v1/products/**")
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.rewritePath("/api/v1/products/?(<segment>.*)", "/api/v1/products/${segment}")
                                        .circuitBreaker(config ->
                                                config.setName("productCircuitBreaker")
                                                        .setFallbackUri("forward:/product-fallback")))
                        .uri("lb://PRODUCT")
                )
                .route(predicateSpec -> predicateSpec.path("/api/v1/discounts/**")
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.rewritePath("/api/v1/discounts/?(<segment>.*)", "/api/v1/discounts/${segment}")
                                        .circuitBreaker(config ->
                                                config.setName("discountCircuitBreaker")
                                                        .setFallbackUri("forward:/discount-fallback")))
                        .uri("lb://DISCOUNT")
                )
                .build();
    }

}
