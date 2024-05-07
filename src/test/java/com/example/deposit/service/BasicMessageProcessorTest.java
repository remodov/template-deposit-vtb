package com.example.deposit.service;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.async.model.CreateProductRequestInnerEventBody;
import com.example.deposit.config.ObjectMapperConfig;
import com.example.deposit.repository.RequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

class BasicMessageProcessorTest {
    private final RequestRepository requestRepository = Mockito.mock(RequestRepository.class);
    private final BasicMessageProcessor basicMessageProcessor = new BasicMessageProcessor(requestRepository);

    {
        basicMessageProcessor.setObjectMapper(new ObjectMapperConfig().objectMapper());
    }

    @Test
    void processMessage() {
        var requestInnerEvent =
                new CreateProductRequestInnerEvent()
                        .id(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now())
                        .body(new CreateProductRequestInnerEventBody()
                                .sum(BigDecimal.ONE)
                        );

        basicMessageProcessor.processMessage(requestInnerEvent);
    }
}