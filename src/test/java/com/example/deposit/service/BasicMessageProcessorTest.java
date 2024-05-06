package com.example.deposit.service;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.async.model.CreateProductRequestInnerEventBody;
import com.example.deposit.repository.RequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.support.DefaultMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BasicMessageProcessorTest {
    @Autowired
    RequestRepository basicRepository;

    @Autowired
    BasicMessageProcessor processor;

    @Autowired
    CamelContext camelContext;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RequestRepository requestRepository;

    @Test
    void shouldProcessAnotherMessage() throws JsonProcessingException {
        String messageId = UUID.randomUUID().toString();
        CreateProductRequestInnerEvent requestInnerEvent = new CreateProductRequestInnerEvent();
        requestInnerEvent.setId(messageId);
        requestInnerEvent.setTimestamp(OffsetDateTime.now());

        CreateProductRequestInnerEventBody requestInnerEventBody = new CreateProductRequestInnerEventBody();
        requestInnerEventBody.setSum(BigDecimal.ONE);
        requestInnerEvent.setBody(requestInnerEventBody);

        String rowRepresentation = objectMapper.writeValueAsString(requestInnerEvent);
        Exchange exchange = new DefaultExchange(camelContext);
        Message message = new DefaultMessage(camelContext);
        message.setBody(rowRepresentation);
        exchange.setMessage(message);

        processor.process(exchange);

        verify(requestRepository, times(1)).save(any());
    }
}