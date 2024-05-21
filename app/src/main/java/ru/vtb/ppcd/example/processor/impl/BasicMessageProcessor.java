package ru.vtb.ppcd.example.processor.impl;

import ru.vtb.ppcd.example.entity.RequestEntity;
import ru.vtb.ppcd.example.processor.ExchangeContext;
import ru.vtb.ppcd.example.processor.MessageProcessor;
import ru.vtb.ppcd.example.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent;

import java.util.UUID;


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
