package com.example.deposit.processor.impl;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.async.model.CreateProductResponseInnerEvent;
import com.example.deposit.async.model.CreateProductResponseInnerEventBody;
import com.example.deposit.entity.RequestEntity;
import com.example.deposit.processor.ExchangeContext;
import com.example.deposit.processor.MessageProcessorFunction;
import com.example.deposit.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@AllArgsConstructor
public class BasicMessageProcessorFunction
        implements MessageProcessorFunction<CreateProductRequestInnerEvent, CreateProductResponseInnerEvent> {
    private final RequestRepository requestRepository;
    private final ObjectMapper objectMapper;

    @Override
    public CreateProductResponseInnerEvent processMessage(
            ExchangeContext<CreateProductRequestInnerEvent> exchangeContext
    ) {
        var message = exchangeContext.getMessage();
        exchangeContext.getHeaders().put("new", message.getId());

        RequestEntity productRequest = RequestEntity.builder()
                .initialDate(message.getTimestamp())
                .sum(message.getBody().getSum())
                .requestId(UUID.fromString(message.getId()))
                .build();

        requestRepository.save(productRequest);

        return new CreateProductResponseInnerEvent().body(
                new CreateProductResponseInnerEventBody()
                        .productId(message.getId())
        );
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
