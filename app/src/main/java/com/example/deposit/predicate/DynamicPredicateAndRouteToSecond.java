package com.example.deposit.predicate;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicPredicateAndRouteToSecond implements CustomDynamicPredicate<CreateProductRequestInnerEvent> {

    public final ObjectMapper objectMapper;

    @Override
    public boolean matchesMessage(CreateProductRequestInnerEvent createProductRequestInnerEvent) {
        var message = createProductRequestInnerEvent.getBody();
        log.debug("createProduct: {}", message);
        return message.getSum().toString().contains("1000");
    }

    @Override
    public Class<CreateProductRequestInnerEvent> matchesClass() {
        return CreateProductRequestInnerEvent.class;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
