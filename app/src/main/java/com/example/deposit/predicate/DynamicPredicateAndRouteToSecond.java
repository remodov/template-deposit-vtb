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
@Getter
@Component
@RequiredArgsConstructor
public class DynamicPredicateAndRouteToSecond implements CustomDynamicPredicate<CreateProductRequestInnerEvent> {

    public final ObjectMapper objectMapper;
    public final ApplicationConfig applicationConfig;

    @Value("${application.routes.first-in-last-out-id.out[1].out-topic}")
    public String routeTo;

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
    public String getRouteTo() {
        return routeTo;
    }
}
