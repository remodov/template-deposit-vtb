package ru.vtb.ppcd.example.route.impl;

import ru.vtb.ppcd.example.config.ApplicationConfig;
import ru.vtb.ppcd.example.config.RouteId;
import ru.vtb.ppcd.example.processor.impl.BasicMessageProcessorFunction;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class FromTransformToRoute extends RouteBuilder {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {
        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId());
        from(routePath.in())
                .id(getRouteId().name())
                .log(LoggingLevel.INFO, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .bean(BasicMessageProcessorFunction.class)
                .to(routePath.out());
    }

    private RouteId getRouteId() {
        return RouteId.FROM_TRANSFORM_TO_ID;
    }
}
