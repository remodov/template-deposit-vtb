package com.example.deposit.service.impl;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.service.ExchangeContext;
import com.example.deposit.service.MessageProcessorFunction;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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
