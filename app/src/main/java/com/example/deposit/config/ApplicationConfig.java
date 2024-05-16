package com.example.deposit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private final Map<RouteId, RoutePath> routes;

    public RoutePath getRoutePathById(RouteId routeId) {
        return routes.get(routeId);
    }
}
