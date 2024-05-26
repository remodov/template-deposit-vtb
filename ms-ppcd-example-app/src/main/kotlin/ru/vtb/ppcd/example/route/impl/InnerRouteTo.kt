package org.example.ru.vtb.ppcd.example.route.impl

import org.apache.camel.builder.RouteBuilder
import org.example.ru.vtb.ppcd.example.config.RouteConfig
import org.example.ru.vtb.ppcd.example.model.RouteId
import org.springframework.stereotype.Component

@Component
class InnerRouteTo(
    private val routeConfig: RouteConfig,
) : RouteBuilder() {
    private val routeId: RouteId = RouteId.DIRECT_OUT_TOPIC_ID

    override fun configure(): Unit =
        routeConfig.getRoutePathWithIdById(routeId)
            .let { routePath ->
                from(routePath.`in`)
                    .id(routePath.routeId.name)
                    .to(routePath.out)
            }
}