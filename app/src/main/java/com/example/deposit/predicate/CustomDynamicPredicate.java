package com.example.deposit.predicate;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public interface CustomDynamicPredicate<T> extends Predicate {


    ObjectMapper getObjectMapper();

    @SneakyThrows
    @Override
    default boolean matches(Exchange exchange) {
        var message = getObjectMapper().readValue(
                exchange.getMessage().getBody().toString(),
                matchesClass()
        );
        return matchesMessage(message);
    }

    String getRouteTo();

    boolean matchesMessage(T message);

    Class<T> matchesClass();

}
