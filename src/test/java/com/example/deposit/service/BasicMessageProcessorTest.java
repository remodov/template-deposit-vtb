package com.example.deposit.service;

import com.example.deposit.entity.BasicEntity;
import com.example.deposit.repository.BasicRepository;
import lombok.SneakyThrows;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.support.DefaultMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BasicMessageProcessorTest {
    @Autowired
    BasicRepository basicRepository;

    @Autowired
    BasicMessageProcessor processor;

    @Autowired
    CamelContext camelContext;

    @Test
    void shouldProcessAnotherMessage() {
        String message = "shouldProcessAnotherMessage";
        processor.processAnotherMessage(message);
        BasicEntity basicEntity = basicRepository.findBasicEntityByMessage(message);
        assertNotNull(basicEntity);
        assertEquals(message, basicEntity.getMessage());
        basicRepository.delete(basicEntity);
    }

    @Test
    @SneakyThrows
    void shouldProcess() {

        String messageBody = "shouldProcess";
        Exchange exchange = new DefaultExchange(camelContext);
        Message message1 = new DefaultMessage(camelContext);
        message1.setBody(messageBody);
        exchange.setMessage(message1);

        assertDoesNotThrow(() -> processor.process(exchange));

        BasicEntity basicEntity = basicRepository.findBasicEntityByMessage(messageBody);
        assertNotNull(basicEntity);
        assertEquals(messageBody, basicEntity.getMessage());

        basicRepository.delete(basicEntity);
    }
}