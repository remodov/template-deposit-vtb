package ru.vtb.ppcd.example.route.impl;

import ru.vtb.ppcd.example.config.ApplicationConfig;
import ru.vtb.ppcd.example.config.RouteId;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InnerRouteTo extends RouteBuilder {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {
        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId());
        from(routePath.in())
                .id(getRouteId().name())
                .to(routePath.out());
    }

    private RouteId getRouteId() {
        return RouteId.DIRECT_OUT_TOPIC_ID;
    }
}
