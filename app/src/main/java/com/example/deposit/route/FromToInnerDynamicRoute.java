package com.example.deposit.route;

import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RoutePath;
import com.example.deposit.predicate.TestAnotherDynamicPredicate;
import com.example.deposit.predicate.TestDynamicPredicate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.example.deposit.config.RouteId.FIRST_IN_LAST_OUT_ID;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FromToInnerDynamicRoute extends RouteBuilder {

    ApplicationConfig applicationConfig;
    TestAnotherDynamicPredicate testAnotherDynamicPredicate;
    TestDynamicPredicate testDynamicPredicate;

    @Override
    public void configure() {

        RoutePath routePath = applicationConfig.getRoutePathById(getRouteId());

        from(routePath.in())
                .id(getRouteId().name())
                .choice()
                .when(testDynamicPredicate)
                .log(LoggingLevel.DEBUG, "Условие 1: офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .to(testDynamicPredicate.getRoutePath().out())
                .when(testAnotherDynamicPredicate)
                .log(LoggingLevel.INFO, "Условие 2: офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .to(testAnotherDynamicPredicate.getRoutePath().out())
                .otherwise()
                .to("log:com.example.deposit.route?level=DEBUG");
    }

    private RouteId getRouteId() {
        return FIRST_IN_LAST_OUT_ID;
    }


}
