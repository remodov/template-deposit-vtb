package com.example.deposit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MessageProcessor<T> implements Processor {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public void process(Exchange exchange) {
        var message = objectMapper.readValue(
                exchange.getMessage().getBody().toString(),
                processClass()
        );

        processMessage(message);
    }

    public abstract void processMessage(T message);

    public abstract Class<T> processClass();
}
