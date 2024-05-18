package com.example.deposit.route.impl;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RoutePath;
import com.example.deposit.service.ErrorHandler;
import com.example.deposit.service.MessageProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;


@AllArgsConstructor
public abstract class FromToExceptionRoute<T> extends RouteBuilder {
    private final ApplicationConfig applicationConfig;
    private final ErrorHandler errorHandler;
    private final MessageProcessor<T> messageProcessor;

    @Override
    public void configure() {
        RoutePath routePath = applicationConfig.getRoutePathById(getRouteId());

        from(routePath.in())
                .id(getRouteId().name())
                .log(LoggingLevel.DEBUG, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .process(messageProcessor)
                .to(routePath.out())
                .onException(Exception.class)
                .handled(true)
                .log("Error occurred: ${exception.message}")
                .process(errorHandler)
                .to(routePath.out());
    }

    public abstract RouteId getRouteId();
}
