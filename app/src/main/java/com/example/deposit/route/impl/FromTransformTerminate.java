package com.example.deposit.route.impl;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.processor.impl.BasicMessageProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import static com.example.deposit.config.RouteId.FROM_TRANSFORM_TERMINATE_ID;



@Component
@AllArgsConstructor
public class FromTransformTerminate extends RouteBuilder {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {
        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId()).routeSourceDestination();

        from(routePath.source())
                .id(getRouteId().name())
                .log(LoggingLevel.INFO, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .bean(BasicMessageProcessor.class);
    }

    private RouteId getRouteId() {
        return FROM_TRANSFORM_TERMINATE_ID;
    }
}
