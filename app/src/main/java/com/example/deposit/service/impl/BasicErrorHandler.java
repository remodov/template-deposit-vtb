package com.example.deposit.service.impl;

import com.example.deposit.async.model.Error;
import com.example.deposit.service.ErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class BasicErrorHandler extends ErrorHandler {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void processMessage(Exchange exchange, Exception exception) {
        var error = new Error()
                .message(exception.getLocalizedMessage())
                .code(Error.CodeEnum.INTERNAL_SERVER_ERROR);

        var outcomeMessage = objectMapper.writeValueAsString(error);

        exchange.getIn().setBody(outcomeMessage);

        log.error("Error occurred {}", exchange.getMessage(), exception);
    }
}
