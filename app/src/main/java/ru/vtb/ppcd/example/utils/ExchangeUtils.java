package ru.vtb.ppcd.example.utils;

import ru.vtb.ppcd.example.processor.ExchangeContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.camel.Exchange;

@UtilityClass
public class ExchangeUtils {

    @SneakyThrows
    public static <T> ExchangeContext<T> toExchangeContext(
            Exchange exchange,
            ObjectMapper objectMapper,
            Class<T> processClass
    ) {
        var incomeMessage = exchange.getMessage().getBody().toString();
        var incomeHeaders = exchange.getIn().getHeaders();
        var messageId = exchange.getIn().getMessageId();

        return ExchangeContext.<T>builder()
                .headers(incomeHeaders)
                .messageId(messageId)
                .message(objectMapper.readValue(incomeMessage, processClass))
                .build();
    }

    @SneakyThrows
    public static <T> void updateExchange(
            Exchange exchange,
            ObjectMapper objectMapper,
            ExchangeContext<T> exchangeContext
    ) {
        var outcomeMessage = objectMapper.writeValueAsString(exchangeContext.getMessage());
        exchange.getMessage().setBody(outcomeMessage);

        var incomeHeaders = exchange.getIn().getHeaders();
        incomeHeaders.forEach((s, o) -> exchange.getIn().setHeader(s, o));
    }
}
