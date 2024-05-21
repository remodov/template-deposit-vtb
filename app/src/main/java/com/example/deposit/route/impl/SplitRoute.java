package com.example.deposit.route.impl;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.processor.impl.NoopMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import static com.example.deposit.config.RouteId.FIRST_IN_LAST_OUT_ID;
import static com.example.deposit.config.RouteId.FROM_TRANSFORM_TO_ID;
import static com.example.deposit.config.RouteId.SPLIT_ID;


@Component
@RequiredArgsConstructor
public class SplitRoute extends RouteBuilder {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {
        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId()).routeSourceDestination();

        var routePath1 = applicationConfig.getRoutePathWithIdById(FIRST_IN_LAST_OUT_ID).routeSourceDestination();
        var routePath2 = applicationConfig.getRoutePathWithIdById(FROM_TRANSFORM_TO_ID).routeSourceDestination();

        from(routePath.source())
                .id(getRouteId().name())
                .bean(NoopMessageProcessor.class)
                .to(routePath1.source())
                .to(routePath2.source())
                .bean(NoopMessageProcessor.class)
                .bean(NoopMessageProcessor.class);
    }

    private RouteId getRouteId() {
        return SPLIT_ID;
    }
}
