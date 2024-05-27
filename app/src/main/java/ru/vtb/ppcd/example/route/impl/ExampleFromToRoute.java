package ru.vtb.ppcd.example.route.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.vtb.ppcd.example.config.ApplicationConfig;
import ru.vtb.ppcd.example.config.RouteId;
import ru.vtb.ppcd.example.config.RoutePathWithId;
import ru.vtb.ppcd.example.processor.ErrorHandler;
import ru.vtb.ppcd.example.processor.MessageProcessor;
import ru.vtb.ppcd.example.processor.impl.OutboxProcessorExample;
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
    private final OutboxProcessorExample outboxProcessorExample;

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

    public OutboxProcessorExample getOutboxProcessorExample() {
        return outboxProcessorExample;
    }
}
