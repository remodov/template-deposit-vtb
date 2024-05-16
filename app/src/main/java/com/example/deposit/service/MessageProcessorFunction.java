package com.example.deposit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.deposit.utils.ExchangeUtils.toExchangeContext;
import static com.example.deposit.utils.ExchangeUtils.updateExchange;


public abstract class MessageProcessorFunction<T, R> implements Processor {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public void process(Exchange exchange) {
        var exchangeContext = toExchangeContext(exchange, objectMapper, processClass());

        R processedMessage = processMessage(exchangeContext);

        ExchangeContext<R> updatedExchangeContext = ExchangeContext.<R>builder()
                .headers(exchangeContext.getHeaders())
                .messageId(exchangeContext.getMessageId())
                .message(processedMessage)
                .build();

        updateExchange(exchange, objectMapper, updatedExchangeContext);
    }

    public abstract R processMessage(ExchangeContext<T> exchangeContext);

    public abstract Class<T> processClass();
}
