package com.example.deposit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import static com.example.deposit.utils.ExchangeUtils.toExchangeContext;
import static com.example.deposit.utils.ExchangeUtils.updateExchange;


public interface MessageProcessorFunction<T, R> extends Processor {

    default void process(Exchange exchange) {
        var exchangeContext = toExchangeContext(exchange, getObjectMapper(), processClass());

        R processedMessage = processMessage(exchangeContext);

        ExchangeContext<R> updatedExchangeContext = ExchangeContext.<R>builder()
                .headers(exchangeContext.getHeaders())
                .messageId(exchangeContext.getMessageId())
                .message(processedMessage)
                .build();

        updateExchange(exchange, getObjectMapper(), updatedExchangeContext);
    }

    R processMessage(ExchangeContext<T> exchangeContext);

    Class<T> processClass();

    ObjectMapper getObjectMapper();
}
