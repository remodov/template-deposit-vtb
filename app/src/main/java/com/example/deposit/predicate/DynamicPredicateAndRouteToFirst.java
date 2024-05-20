package com.example.deposit.predicate;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.config.ApplicationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicPredicateAndRouteToFirst implements CustomDynamicPredicate<CreateProductRequestInnerEvent> {

    @Getter
    public final ObjectMapper objectMapper;
    public final ApplicationConfig applicationConfig;

    // вариант 1
    @Value("${application.routes.first-in-last-out-id.out[0].out-topic}")
    public String routeTo;

    @Override
    public boolean matchesMessage(CreateProductRequestInnerEvent message) {
        var createProduct = message.getBody();
        log.debug("createProduct: {}", createProduct);
        return message.getBody().getSum().toString().contains("103");
    }

    @Override
    public Class<CreateProductRequestInnerEvent> matchesClass() {
        return CreateProductRequestInnerEvent.class;
    }

    @Override
    public String getRouteTo() {
        return routeTo;
    }
}
