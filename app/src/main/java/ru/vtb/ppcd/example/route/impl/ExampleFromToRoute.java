package ru.vtb.ppcd.example.route.impl;

import ru.vtb.ppcd.example.config.ApplicationConfig;
import ru.vtb.ppcd.example.config.RouteId;
import ru.vtb.ppcd.example.config.RoutePathWithId;
import ru.vtb.ppcd.example.processor.ErrorHandler;
import ru.vtb.ppcd.example.processor.MessageProcessor;
import ru.vtb.ppcd.example.route.FromToExceptionRoute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent;

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
