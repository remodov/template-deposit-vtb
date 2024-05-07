package com.example.deposit.route;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RoutePath;
import com.example.deposit.service.impl.NoopMessageProcessor;
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
        RoutePath routePath = applicationConfig.getRoutePathById(getRouteId());

        RoutePath routePath1 = applicationConfig.getRoutePathById(FIRST_IN_LAST_OUT_ID);
        RoutePath routePath2 = applicationConfig.getRoutePathById(FROM_TRANSFORM_TO_ID);

        from(routePath.in())
                .id(getRouteId().name())
                .bean(NoopMessageProcessor.class)
                .to(routePath1.in())
                .to(routePath2.in())
                .bean(NoopMessageProcessor.class)
                .bean(NoopMessageProcessor.class);
    }

    private RouteId getRouteId() {
        return SPLIT_ID;
    }
}
