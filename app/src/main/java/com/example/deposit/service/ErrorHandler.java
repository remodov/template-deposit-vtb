package com.example.deposit.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class ErrorHandler implements Processor {

    @Override
    public void process(Exchange exchange) {
        Exception caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        processMessage(exchange, caused);
    }

    public abstract void processMessage(Exchange exchange, Exception exception);
}
