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

import static com.example.deposit.config.RouteId.DIRECT_OUT_TOPIC_ID;

@Slf4j
@Getter
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestDynamicPredicate implements CustomDynamicPredicate<CreateProductRequestInnerEvent> {

    ObjectMapper objectMapper;
    ApplicationConfig applicationConfig;

    @Override
    public boolean matchesMessage(CreateProductRequestInnerEvent createProductRequestInnerEvent) {
        var message = createProductRequestInnerEvent.getBody();
        log.debug("createProduct: {}", message);
        return  message.getSum().toString().contains("1000");
    }

    @Override
    public Class<CreateProductRequestInnerEvent> matchesClass() {
        return CreateProductRequestInnerEvent.class;
    }

    @Override
    public RoutePath getRoutePath() {
        return applicationConfig.getRoutePathById(DIRECT_OUT_TOPIC_ID);
    }
}
