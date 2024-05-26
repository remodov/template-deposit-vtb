package org.example.ru.vtb.ppcd.example.route.impl

import org.apache.camel.builder.RouteBuilder
import org.example.ru.vtb.ppcd.example.config.RouteConfig
import org.example.ru.vtb.ppcd.example.model.RouteId
import org.example.ru.vtb.ppcd.example.processor.impl.NoopMessageProcessor
import org.springframework.stereotype.Component

@Component
class SplitRoute(
    private val routeConfig: RouteConfig,
) : RouteBuilder() {
    private val routeId: RouteId = RouteId.SPLIT_ID

    override fun configure(): Unit =
        routeConfig.getRoutePathWithIdById(routeId)
            .let { routePath ->
                val routePath1 = routeConfig.getRoutePathWithIdById(RouteId.FIRST_IN_LAST_OUT_ID)
                val routePath2 = routeConfig.getRoutePathWithIdById(RouteId.FROM_TRANSFORM_TO_ID)

                from(routePath.`in`)
                    .id(routeId.name)
                    .bean(NoopMessageProcessor::class.java)
                    .to(routePath1.`in`)
                    .to(routePath2.`in`)
                    .bean(NoopMessageProcessor::class.java)
                    .bean(NoopMessageProcessor::class.java)
            }
}