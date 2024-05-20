package com.example.deposit.processor;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.async.model.CreateProductRequestInnerEventBody;
import com.example.deposit.processor.impl.BasicMessageProcessor;
import com.example.deposit.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

class BasicMessageProcessorTest {
    private final RequestRepository requestRepository = Mockito.mock(RequestRepository.class);
    private final BasicMessageProcessor basicMessageProcessor
            = new BasicMessageProcessor(
            requestRepository, new ObjectMapper());

    @Test
    void processMessage() {
        var requestInnerEvent =
                new CreateProductRequestInnerEvent()
                        .id(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now())
                        .body(new CreateProductRequestInnerEventBody()
                                .sum(BigDecimal.ONE)
                        );

        basicMessageProcessor.processMessage(
                ExchangeContext.<CreateProductRequestInnerEvent>builder()
                        .message(requestInnerEvent)
                        .build()
        );
    }
}