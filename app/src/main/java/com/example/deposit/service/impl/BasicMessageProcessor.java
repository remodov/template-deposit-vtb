package com.example.deposit.service.impl;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.entity.RequestEntity;
import com.example.deposit.repository.RequestRepository;
import com.example.deposit.service.ExchangeContext;
import com.example.deposit.service.MessageProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class BasicMessageProcessor extends MessageProcessor<CreateProductRequestInnerEvent> {
    private final RequestRepository requestRepository;

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
    public Class<CreateProductRequestInnerEvent> processClass() {
        return CreateProductRequestInnerEvent.class;
    }
}
