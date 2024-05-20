package com.example.deposit.route.impl;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.service.impl.BasicMessageProcessorFunction;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import static com.example.deposit.config.RouteId.FROM_TRANSFORM_TO_ID;



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
                .to(routePath.out().get(0).get("out-topic"));
    }

    private RouteId getRouteId() {
        return FROM_TRANSFORM_TO_ID;
    }
}
