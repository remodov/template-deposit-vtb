package com.example.deposit.route;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RouteIdPath;
import com.example.deposit.predicate.DynamicPredicateAndRouteToFirst;
import com.example.deposit.predicate.DynamicPredicateAndRouteToSecond;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.deposit.config.DestinationId.ID_1;
import static com.example.deposit.config.DestinationId.ID_2;
import static com.example.deposit.config.RouteId.FIRST_IN_LAST_OUT_ID;

@Component
@AllArgsConstructor
public class FromToInnerDynamicRoute extends RouteBuilder {

    private final ApplicationConfig applicationConfig;

    // вариант 2
    private final DynamicPredicateAndRouteToFirst dynamicPredicateAndRouteToFirst;
    private final DynamicPredicateAndRouteToSecond dynamicPredicateAndRouteToSecond;

    @Override
    public void configure() {

        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId()).routeSourceDestination();

        from(routePath.source())
                .id(getRouteId().name())
                .choice()
                .when(dynamicPredicateAndRouteToFirst)
                .log(LoggingLevel.INFO, "Условие роута выполнено: " + ID_1)
                .to(routePath.destination()
                        .stream()
                        .filter(r -> Objects.equals(ID_1, r.id()))
                        .findFirst()
                        .map(RouteIdPath::path)
                        .orElseThrow()
                )
                .when(dynamicPredicateAndRouteToSecond)
                .log(LoggingLevel.INFO, "Условие роута выполнено: " + ID_2)
                .to(routePath.destination()
                        .stream()
                        .filter(r -> Objects.equals(ID_2, r.id()))
                        .findFirst()
                        .map(RouteIdPath::path)
                        .orElseThrow()
                );

    }

    private RouteId getRouteId() {
        return FIRST_IN_LAST_OUT_ID;
    }


}
