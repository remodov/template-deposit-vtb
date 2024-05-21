package com.example.deposit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;


@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private final Map<RouteId, RouteSourceDestination> routes;

    public RouteSourceDestinationWithRouteId getRoutePathWithIdById(RouteId routeId) {
        var routePath = routes.get(routeId);
        return new RouteSourceDestinationWithRouteId(routeId, routePath);
    }
}
