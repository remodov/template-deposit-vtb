package org.example.ru.vtb.ppcd.example.route

import org.apache.camel.LoggingLevel
import org.apache.camel.builder.RouteBuilder
import org.example.ru.vtb.ppcd.example.model.RoutePathWithId
import org.example.ru.vtb.ppcd.example.processor.ErrorHandler
import org.example.ru.vtb.ppcd.example.processor.MessageProcessor

abstract class FromToExceptionRoute<T> : RouteBuilder() {
    override fun configure() {
        onException(Exception::class.java)
            .handled(true)
            .log("Error occurred: \${exception.message}")
            .process(getErrorHandler())
            .process(getMessageProcessor())
            .to(getRoutePathWithId().out)

        from(getRoutePathWithId().`in`)
            .transacted()
            .id(getRoutePathWithId().routeId.name)
            .log(LoggingLevel.DEBUG, "офсет - [\${header.kafka.OFFSET}], тело - [\${body}]")
            .to(getRoutePathWithId().out)
    }

    abstract fun getRoutePathWithId(): RoutePathWithId

    abstract fun getErrorHandler(): ErrorHandler

    abstract fun getMessageProcessor(): MessageProcessor<T>
}