package com.example.deposit.service.impl;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.async.model.CreateProductResponseInnerEvent;
import com.example.deposit.async.model.CreateProductResponseInnerEventBody;
import com.example.deposit.entity.RequestEntity;
import com.example.deposit.repository.RequestRepository;
import com.example.deposit.service.MessageProcessorFunction;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class BasicMessageProcessorFunction
        extends MessageProcessorFunction<CreateProductRequestInnerEvent, CreateProductResponseInnerEvent> {
    private final RequestRepository requestRepository;

    @Override
    public CreateProductResponseInnerEvent processMessage(
            CreateProductRequestInnerEvent createProductRequestInnerEvent) {
        var createProduct = createProductRequestInnerEvent.getBody();

        RequestEntity productRequest = RequestEntity.builder()
                .initialDate(createProductRequestInnerEvent.getTimestamp())
                .sum(createProduct.getSum())
                .requestId(UUID.fromString(createProductRequestInnerEvent.getId()))
                .build();

        requestRepository.save(productRequest);

        return new CreateProductResponseInnerEvent().body(
                new CreateProductResponseInnerEventBody()
                        .productId(createProductRequestInnerEvent.getId())
        );
    }

    @Override
    public Class<CreateProductRequestInnerEvent> processClass() {
        return CreateProductRequestInnerEvent.class;
    }
}
