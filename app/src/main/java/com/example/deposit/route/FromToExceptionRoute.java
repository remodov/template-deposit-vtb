package com.example.deposit.route;

import com.example.deposit.config.RouteIdPath;
import com.example.deposit.config.RouteSourceDestinationWithRouteId;
import com.example.deposit.processor.ErrorHandler;
import com.example.deposit.processor.MessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;


public abstract class FromToExceptionRoute<T> extends RouteBuilder {

    @Override
    public void configure() {

        var routePathWithId = getRoutePathWithId().routeSourceDestination();

        onException(Exception.class)
                .handled(true)
                .log("Error occurred: ${exception.message}")
                .process(getErrorHandler())
                .process(getMessageProcessor())
                .to(routePathWithId.destination()
                        .stream()
                        .findFirst()
                        .map(RouteIdPath::path)
                        .orElseThrow()
                );

        from(routePathWithId.source())
                .transacted()
                .id(getRoutePathWithId().routeId().name())
                .log(LoggingLevel.DEBUG, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .to(routePathWithId.destination()
                        .stream()
                        .findFirst()
                        .map(RouteIdPath::path)
                        .orElseThrow());
    }

    public abstract RouteSourceDestinationWithRouteId getRoutePathWithId();

    public abstract ErrorHandler getErrorHandler();

    public abstract MessageProcessor<T> getMessageProcessor();
}
