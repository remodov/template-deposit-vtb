package com.example.deposit.predicate;

import com.example.deposit.async.model.CreateProductRequestInnerEvent;
import com.example.deposit.config.ApplicationConfig;
import com.example.deposit.config.RoutePath;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.deposit.config.RouteId.FROM_TRANSFORM_TO_ID;

@Slf4j
@Getter
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestAnotherDynamicPredicate implements CustomDynamicPredicate<CreateProductRequestInnerEvent> {


    ApplicationConfig applicationConfig;
    ObjectMapper objectMapper;

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
    public RoutePath getRoutePath() {
        return applicationConfig.getRoutePathById(FROM_TRANSFORM_TO_ID);
    }
}
