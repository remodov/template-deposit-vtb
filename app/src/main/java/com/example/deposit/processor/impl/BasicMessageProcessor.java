package com.example.deposit.processor.impl;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.entity.RequestEntity;
import com.example.deposit.processor.ExchangeContext;
import com.example.deposit.processor.MessageProcessor;
import com.example.deposit.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@AllArgsConstructor
public class BasicMessageProcessor implements MessageProcessor<CreateProductRequestInnerEvent> {
    private final RequestRepository requestRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void processMessage(ExchangeContext<CreateProductRequestInnerEvent> exchangeContext) {
        var message = exchangeContext.getMessage();

        RequestEntity productRequest = RequestEntity.builder()
                .initialDate(message.getTimestamp())
                .sum(message.getBody().getSum())
                .requestId(UUID.fromString(message.getId()))
                .build();


        requestRepository.save(productRequest);
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public Class<CreateProductRequestInnerEvent> processClass() {
        return CreateProductRequestInnerEvent.class;
    }
}
