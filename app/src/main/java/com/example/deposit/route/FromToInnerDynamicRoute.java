package com.example.deposit.route;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.predicate.CustomDynamicPredicate;
import com.example.deposit.predicate.DynamicPredicateAndRouteToFirst;
import com.example.deposit.predicate.DynamicPredicateAndRouteToSecond;
import lombok.AllArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import static com.example.deposit.config.RouteId.FIRST_IN_LAST_OUT_ID;

@Component
@AllArgsConstructor
public class FromToInnerDynamicRoute extends RouteBuilder {

    private final ApplicationConfig applicationConfig;
    private final ApplicationContext applicationContext;

    // вариант 2
    private final DynamicPredicateAndRouteToFirst dynamicPredicateAndRouteToFirst;
    private final DynamicPredicateAndRouteToSecond dynamicPredicateAndRouteToSecond;

    // вариант 3
    @Value("${application.routes.first-in-last-out-id.out[0].out-topic}")
    public String routeToFirst;
    @Value("${application.routes.first-in-last-out-id.out[0].out-topic}")
    public String routeToSecond;

    @Override
    public void configure() {

        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId());
        var routes = applicationContext.getBeansOfType(CustomDynamicPredicate.class);

        var choiceDefinition = from(routePath.in())
                .id(getRouteId().name())
                .choice();

        routes.values().forEach(route -> {
            choiceDefinition
                    .when(route)
                    .log(LoggingLevel.DEBUG, "Условие роута выполнено: " + route.getClass().getName())
                    .to(route.getRouteTo());
        });

        choiceDefinition
                .otherwise()
                .to("log:com.example.deposit.predicate.CustomDynamicPredicate?level=DEBUG");

        log.debug("");
    }

    // второй вариант
    public void configure2() {

        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId());

        var choiceDefinition = from(routePath.in())
                .id(getRouteId().name())
                .choice()
                .when(dynamicPredicateAndRouteToFirst)
                .log(LoggingLevel.DEBUG, "Условие роута выполнено: " + routePath.out().get(0).get("out-id"))
                .to(routePath.out().get(0).get("out-topic"))
                .when(dynamicPredicateAndRouteToSecond)
                .log(LoggingLevel.DEBUG, "Условие роута выполнено: " + routePath.out().get(1).get("out-id"))
                .to(routePath.out().get(1).get("out-topic"))
                .otherwise()
                .to("log:com.example.deposit.predicate.CustomDynamicPredicate?level=DEBUG");

        log.debug("");
    }

    // второй вариант
    public void configure3() {

        var routePath = applicationConfig.getRoutePathWithIdById(getRouteId());

        var choiceDefinition = from(routePath.in())
                .id(getRouteId().name())
                .choice()
                .when(dynamicPredicateAndRouteToFirst)
                .log(LoggingLevel.DEBUG, "Условие роута выполнено: " + routePath.out().get(0).get("out-id"))
                .to(routeToFirst)
                .when(dynamicPredicateAndRouteToSecond)
                .log(LoggingLevel.DEBUG, "Условие роута выполнено: " + routePath.out().get(1).get("out-id"))
                .to(routeToSecond)
                .otherwise()
                .to("log:com.example.deposit.predicate.CustomDynamicPredicate?level=DEBUG");

        log.debug("");
    }

    private RouteId getRouteId() {
        return FIRST_IN_LAST_OUT_ID;
    }


}
