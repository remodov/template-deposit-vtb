package com.example.deposit.route;

import com.example.deposit.config.RoutePathWithId;
import com.example.deposit.processor.ErrorHandler;
import com.example.deposit.processor.MessageProcessor;
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
                .to(getRoutePathWithId().out().get(0).get("out-topic"))
                .log("Error occurred: ${exception.message}")
                .process(getErrorHandler())
                .to(getRoutePathWithId().out().get(0).get("out-topic"));
    }

    public abstract RoutePathWithId getRoutePathWithId();

    public abstract ErrorHandler getErrorHandler();

    public abstract MessageProcessor<T> getMessageProcessor();
}
