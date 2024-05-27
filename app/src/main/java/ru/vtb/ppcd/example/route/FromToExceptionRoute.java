package ru.vtb.ppcd.example.route;

import org.apache.kafka.common.errors.TimeoutException;
import ru.vtb.ppcd.example.config.RoutePathWithId;
import ru.vtb.ppcd.example.processor.ErrorHandler;
import ru.vtb.ppcd.example.processor.MessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import ru.vtb.ppcd.example.processor.impl.OutboxProcessorExample;


public abstract class FromToExceptionRoute<T> extends RouteBuilder {

    @Override
    public void configure() {
        onException(TimeoutException.class)
                .handled(true)
                .log("Timeout occurred while sending message to Kafka")
                .process(getOutboxProcessor())
                .end();

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

    public abstract OutboxProcessorExample getOutboxProcessor();
}
