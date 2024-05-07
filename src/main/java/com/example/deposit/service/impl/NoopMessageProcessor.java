package com.example.deposit.service.impl;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.service.MessageProcessorFunction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class NoopMessageProcessor extends MessageProcessorFunction<CreateProductRequestInnerEvent, CreateProductRequestInnerEvent> {
    @Override
    public CreateProductRequestInnerEvent processMessage(CreateProductRequestInnerEvent someMessage) {
        log.info("NoopMessageProcessor.processMessage: {} {}", someMessage, UUID.randomUUID());
        return someMessage;
    }

    @Override
    public Class<CreateProductRequestInnerEvent> processClass() {
        return CreateProductRequestInnerEvent.class;
    }
}
