package org.example.ru.vtb.ppcd.example.route.impl

import org.example.ru.vtb.ppcd.example.config.RouteConfig
import org.example.ru.vtb.ppcd.example.model.RouteId
import org.example.ru.vtb.ppcd.example.processor.ErrorHandler
import org.example.ru.vtb.ppcd.example.processor.MessageProcessor
import org.example.ru.vtb.ppcd.example.route.FromToExceptionRoute
import org.springframework.stereotype.Component
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent

@Component
class ExampleFromToRoute(
    private val basicErrorHandler: ErrorHandler,
    private val routeConfig: RouteConfig,
    private val basicMessageProcessor: MessageProcessor<CreateProductRequestInnerEvent>
) : FromToExceptionRoute<CreateProductRequestInnerEvent>() {
    private val routeId: RouteId = RouteId.FIRST_IN_LAST_OUT_ID

    override fun getRoutePathWithId() = routeConfig.getRoutePathWithIdById(routeId)

    override fun getErrorHandler() = basicErrorHandler;

    override fun getMessageProcessor() = basicMessageProcessor

}