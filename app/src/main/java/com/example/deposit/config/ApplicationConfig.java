package com.example.deposit.config;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;



@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private final Map<RouteId, RoutePath> routes;

    public RoutePath getRoutePathById(RouteId routeId) {
        return routes.get(routeId);
    }
}
