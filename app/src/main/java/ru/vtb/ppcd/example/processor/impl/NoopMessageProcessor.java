package ru.vtb.ppcd.example.processor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vtb.ppcd.example.processor.ExchangeContext;
import ru.vtb.ppcd.example.processor.MessageProcessorFunction;
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent;

import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class NoopMessageProcessor
        implements MessageProcessorFunction<CreateProductRequestInnerEvent, CreateProductRequestInnerEvent> {
    private final ObjectMapper objectMapper;

    @Override
    public CreateProductRequestInnerEvent processMessage(
            ExchangeContext<CreateProductRequestInnerEvent> exchangeContext
    ) {
        log.info("NoopMessageProcessor.exchangeContext: {} {}", exchangeContext, UUID.randomUUID());
        return exchangeContext.getMessage();
    }

    @Override
    public Class<CreateProductRequestInnerEvent> processClass() {
        return CreateProductRequestInnerEvent.class;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
