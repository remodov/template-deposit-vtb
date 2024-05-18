package com.example.deposit.route;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.route.impl.FromToExceptionRoute;
import com.example.deposit.service.ErrorHandler;
import com.example.deposit.service.MessageProcessor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ExampleFromToRoute extends FromToExceptionRoute<CreateProductRequestInnerEvent> {
    private final ErrorHandler basicErrorHandler;
    private final ApplicationConfig applicationConfig;
    private final MessageProcessor<CreateProductRequestInnerEvent> basicMessageProcessor;

    @Override
    public RouteId getRouteId() {
        return RouteId.FIRST_IN_LAST_OUT_ID;
    }
}
