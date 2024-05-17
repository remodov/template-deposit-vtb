package com.example.deposit.service;

import static com.example.deposit.utils.ExchangeUtils.toExchangeContext;
import static com.example.deposit.utils.ExchangeUtils.updateExchange;

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
        var exchangeContext = toExchangeContext(exchange, objectMapper, processClass());

        processMessage(exchangeContext);

        updateExchange(exchange, objectMapper, exchangeContext);
    }

    public abstract void processMessage(ExchangeContext<T> exchangeContext);

    public abstract Class<T> processClass();
}
