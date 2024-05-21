package com.example.deposit.route.impl;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RouteIdPath;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import static com.example.deposit.config.RouteId.DIRECT_OUT_TOPIC_ID;

@Component
@RequiredArgsConstructor
public class InnerRouteTo extends RouteBuilder {
    private final ApplicationConfig applicationConfig;

    @Override
    public void configure() {

        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId()).routeSourceDestination();

        from(routePath.source())
                .id(getRouteId().name())
                .to(routePath.destination()
                        .stream()
                        .findFirst()
                        .map(RouteIdPath::path)
                        .orElseThrow());
    }

    private RouteId getRouteId() {
        return DIRECT_OUT_TOPIC_ID;
    }
}
