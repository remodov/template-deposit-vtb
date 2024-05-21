package ru.vtb.ppcd.example.processor.impl;

import ru.vtb.ppcd.example.entity.RequestEntity;
import ru.vtb.ppcd.example.processor.ExchangeContext;
import ru.vtb.ppcd.example.processor.MessageProcessorFunction;
import ru.vtb.ppcd.example.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent;
import ru.vtb.ppcd.generated.async.model.CreateProductResponseInnerEvent;
import ru.vtb.ppcd.generated.async.model.CreateProductResponseInnerEventBody;

import java.util.UUID;


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
