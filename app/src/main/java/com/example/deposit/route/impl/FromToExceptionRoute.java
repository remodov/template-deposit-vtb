package com.example.deposit.route.impl;

import com.example.deposit.config.RoutePathWithId;
import com.example.deposit.service.ErrorHandler;
import com.example.deposit.service.MessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;


public abstract class FromToExceptionRoute<T> extends RouteBuilder {

    @Override
    public void configure() {
        from(getRoutePathWithId().in())
                .transacted()
                .id(getRoutePathWithId().routeId().name())
                .log(LoggingLevel.DEBUG, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .process(getMessageProcessor())
                .to(getRoutePathWithId().out())
                .onException(Exception.class)
                .handled(true)
                .log("Error occurred: ${exception.message}")
                .process(getErrorHandler())
                .to(getRoutePathWithId().out());
    }

    public abstract RoutePathWithId getRoutePathWithId();

    public abstract ErrorHandler getErrorHandler();

    public abstract MessageProcessor<T> getMessageProcessor();
}
