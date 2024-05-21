package ru.vtb.ppcd.example.route.impl;

import ru.vtb.ppcd.example.config.ApplicationConfig;
import ru.vtb.ppcd.example.config.RouteId;
import ru.vtb.ppcd.example.processor.impl.BasicMessageProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class FromTransformTerminate extends RouteBuilder {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {
        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId());
        from(routePath.in())
                .id(getRouteId().name())
                .log(LoggingLevel.INFO, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .bean(BasicMessageProcessor.class);
    }

    private RouteId getRouteId() {
        return RouteId.FROM_TRANSFORM_TERMINATE_ID;
    }
}
