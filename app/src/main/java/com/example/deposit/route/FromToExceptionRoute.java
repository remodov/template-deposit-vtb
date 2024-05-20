package com.example.deposit.route;

import com.example.deposit.config.RoutePathWithId;
import com.example.deposit.processor.ErrorHandler;
import com.example.deposit.processor.MessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;


public abstract class FromToExceptionRoute<T> extends RouteBuilder {

    @Override
    public void configure() {
        onException(Exception.class)
                .handled(true)
                .log("Error occurred: ${exception.message}")
                .process(getErrorHandler())
                .process(getMessageProcessor())
                .to(getRoutePathWithId().out());

        from(getRoutePathWithId().in())
                .transacted()
                .id(getRoutePathWithId().routeId().name())
                .log(LoggingLevel.DEBUG, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .to(getRoutePathWithId().out());
    }

    public abstract RoutePathWithId getRoutePathWithId();

    public abstract ErrorHandler getErrorHandler();

    public abstract MessageProcessor<T> getMessageProcessor();
}
