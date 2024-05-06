package com.example.deposit.route;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RoutePath;
import com.example.deposit.service.BasicMessageProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.example.deposit.config.RouteId.FIRST_IN_LAST_OUT_ID;

@Component
@AllArgsConstructor
public class FirstInLastOutRoute extends RouteBuilder implements DepositRouter {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {
        RoutePath routePath = applicationConfig.getRoutePathById(getRouteId());
        from(routePath.getIn())
                .id(getRouteId().name())
                .routeId("first-in")
                .log(LoggingLevel.INFO, "офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .bean(BasicMessageProcessor.class)
                .to(routePath.getOut());
    }

    @Override
    public RouteId getRouteId() {
        return FIRST_IN_LAST_OUT_ID;
    }
}
