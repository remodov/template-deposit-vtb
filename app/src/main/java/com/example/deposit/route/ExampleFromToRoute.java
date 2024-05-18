package com.example.deposit.route;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RouteId;
import com.example.deposit.config.RoutePathWithId;
import com.example.deposit.route.impl.FromToExceptionRoute;
import com.example.deposit.service.ErrorHandler;
import com.example.deposit.service.MessageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleFromToRoute extends FromToExceptionRoute<CreateProductRequestInnerEvent> {
    private final ErrorHandler basicErrorHandler;
    private final ApplicationConfig applicationConfig;
    private final MessageProcessor<CreateProductRequestInnerEvent> basicMessageProcessor;

    @Override
    public RoutePathWithId getRoutePathWithId() {
        return applicationConfig.getRoutePathWithIdById(RouteId.FIRST_IN_LAST_OUT_ID);
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return basicErrorHandler;
    }

    @Override
    public MessageProcessor<CreateProductRequestInnerEvent> getMessageProcessor() {
        return basicMessageProcessor;
    }
}
