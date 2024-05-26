package org.example.ru.vtb.ppcd.example.config

import org.example.ru.vtb.ppcd.example.model.RouteId
import org.example.ru.vtb.ppcd.example.model.RoutePath
import org.example.ru.vtb.ppcd.example.model.RoutePathWithId
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application")
class RouteConfig(
    private val routes: Map<RouteId, RoutePath>
) {
    fun getRoutePathWithIdById(routeId: RouteId): RoutePathWithId {
        return routes[routeId]
            ?.let { RoutePathWithId(routeId, it.`in`, it.out) }
            ?: throw IllegalStateException(
                "ApplicationConfig.getRoutePathWithIdById: " +
                        "не смогли получить маршрут по routeId: [$routeId]"
            )
    }
}