package ru.vtb.ppcd.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;


@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private final Map<RouteId, RoutePath> routes;

    public RoutePathWithId getRoutePathWithIdById(RouteId routeId) {
        var routePath = routes.get(routeId);
        return new RoutePathWithId(routeId, routePath.in(), routePath.out());
    }
}
