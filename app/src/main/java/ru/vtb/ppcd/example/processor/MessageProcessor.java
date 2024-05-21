package ru.vtb.ppcd.example.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import static ru.vtb.ppcd.example.utils.ExchangeUtils.toExchangeContext;
import static ru.vtb.ppcd.example.utils.ExchangeUtils.updateExchange;


public interface MessageProcessor<T> extends Processor {

    default void process(Exchange exchange) {
        var exchangeContext = toExchangeContext(exchange, getObjectMapper(), processClass());

        processMessage(exchangeContext);

        updateExchange(exchange, getObjectMapper(), exchangeContext);
    }

    void processMessage(ExchangeContext<T> exchangeContext);

    ObjectMapper getObjectMapper();

    Class<T> processClass();
}
