package com.example.deposit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Function;
import lombok.SneakyThrows;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class MessageProcessorFunction<T, R> implements Function<Exchange, String> {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public String apply(Exchange exchange) {
        var message = objectMapper.readValue(
                exchange.getMessage().getBody().toString(),
                processClass()
        );
        R r = processMessage(message);
        return objectMapper.writeValueAsString(r);
    }

    public abstract R processMessage(T message);

    public abstract Class<T> processClass();
}
