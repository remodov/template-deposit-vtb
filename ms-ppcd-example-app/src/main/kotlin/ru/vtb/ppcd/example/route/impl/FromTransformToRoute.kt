package org.example.ru.vtb.ppcd.example.route.impl

import org.apache.camel.LoggingLevel
import org.apache.camel.builder.RouteBuilder
import org.example.ru.vtb.ppcd.example.config.RouteConfig
import org.example.ru.vtb.ppcd.example.model.RouteId
import org.example.ru.vtb.ppcd.example.processor.impl.BasicMessageProcessor
import org.springframework.stereotype.Component

@Component
class FromTransformToRoute(
    private val routeConfig: RouteConfig,
) : RouteBuilder() {
    private val routeId: RouteId = RouteId.FROM_TRANSFORM_TERMINATE_ID

    override fun configure(): Unit =
        routeConfig.getRoutePathWithIdById(routeId)
            .let { routePath ->
                from(routePath.`in`)
                    .id(routeId.name)
                    .log(
                        LoggingLevel.INFO, "офсет - [\${header.kafka.OFFSET" +
                                "}], тело - [\${body}]"
                    ).bean(BasicMessageProcessor::class.java)
            }
}