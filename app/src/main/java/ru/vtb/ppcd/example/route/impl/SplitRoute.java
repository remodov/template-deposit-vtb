package ru.vtb.ppcd.example.route.impl;

import ru.vtb.ppcd.example.config.ApplicationConfig;
import ru.vtb.ppcd.example.config.RouteId;
import ru.vtb.ppcd.example.processor.impl.NoopMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SplitRoute extends RouteBuilder {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {
        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId());

        var routePath1 = applicationConfig.getRoutePathWithIdById(RouteId.FIRST_IN_LAST_OUT_ID);
        var routePath2 = applicationConfig.getRoutePathWithIdById(RouteId.FROM_TRANSFORM_TO_ID);

        from(routePath.in())
                .id(getRouteId().name())
                .bean(NoopMessageProcessor.class)
                .to(routePath1.in())
                .to(routePath2.in())
                .bean(NoopMessageProcessor.class)
                .bean(NoopMessageProcessor.class);
    }

    private RouteId getRouteId() {
        return RouteId.SPLIT_ID;
    }
}
