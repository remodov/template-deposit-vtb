package com.example.deposit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

public abstract class MessageProcessorFunction<T, R> implements Function<Exchange, R> {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public R apply(Exchange exchange) {
        var message = objectMapper.readValue(
                exchange.getMessage().getBody().toString(),
                processClass()
        );

        return processMessage(message);
    }

    public abstract R processMessage(T message);

    public abstract Class<T> processClass();
}
