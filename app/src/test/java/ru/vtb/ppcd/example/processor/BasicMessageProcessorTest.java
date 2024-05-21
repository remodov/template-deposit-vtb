package ru.vtb.ppcd.example.processor;

import ru.vtb.ppcd.example.processor.impl.BasicMessageProcessor;
import ru.vtb.ppcd.example.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent;
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEventBody;

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