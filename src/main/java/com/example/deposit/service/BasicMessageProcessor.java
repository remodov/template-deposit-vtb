package com.example.deposit.service;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.entity.RequestEntity;
import com.example.deposit.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

/**
 * BasicMessageProcessor.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BasicMessageProcessor implements Processor {
    private final RequestRepository requestRepository;
    private final ObjectMapper objectMapper;

    /**
     * processMessage().
     */
    @Transactional
    public void processMessage(CreateProductRequestInnerEvent
                                       createProductRequestInnerEvent) {

        var createProduct = createProductRequestInnerEvent.getBody();

        RequestEntity productRequest = RequestEntity.builder()
                .initialDate(createProductRequestInnerEvent.getTimestamp())
                .sum(createProduct.getSum())
                .requestId(UUID.fromString(createProductRequestInnerEvent.getId()))
                .build();

        requestRepository.save(productRequest);
    }

    /**
     * process().
     */
    @Override
    public void process(Exchange exchange) {
        CreateProductRequestInnerEvent request;
        try {
            request = objectMapper
                    .readValue(exchange.getMessage().getBody().toString(),
                            CreateProductRequestInnerEvent.class);
        } catch (Exception ex) {
            log.error("BasicMessageProcessor.process: не смогли обработать exchange - {}, ex - \n{}",
                    exchange.getMessage(), ExceptionUtils.getStackTrace(ex));
            // TODO: send to dead letter queue
            throw new IllegalStateException("BasicMessageProcessor.process: не смогли обработать exchange");
        }

        processMessage(request);
    }
}
