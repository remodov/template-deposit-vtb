package ru.vtb.ppcd.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public interface ErrorHandler extends Processor {

    default void process(Exchange exchange) {
        Exception caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        processMessage(exchange, caused);
    }

    void processMessage(Exchange exchange, Exception exception);
}
