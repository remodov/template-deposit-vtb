package com.example.deposit.processor.impl;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.processor.ExchangeContext;
import com.example.deposit.processor.MessageProcessorFunction;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
