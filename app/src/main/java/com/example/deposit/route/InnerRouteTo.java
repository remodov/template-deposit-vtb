package com.example.deposit.route;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RoutePath;
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
        RoutePath routePath = applicationConfig.getRoutePathById(getRouteId());
        from(routePath.in())
                .id(getRouteId().name())
                .to(routePath.out());
    }

    private RouteId getRouteId() {
        return DIRECT_OUT_TOPIC_ID;
    }
}
