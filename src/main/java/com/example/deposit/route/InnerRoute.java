package com.example.deposit.route;

import com.example.deposit.config.KafkaConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * InnerRoute.
 */
@Component
@RequiredArgsConstructor
public class InnerRoute extends RouteBuilder {
    private final KafkaConfig kafkaConfig;
    private String kafkaBrokers;

    /**
     * setUp().
     */
    @PostConstruct
    public void setUp() {
        kafkaBrokers = "kafka:%s?brokers=" + kafkaConfig.getInnerBrokers()
                + "&groupId=" + kafkaConfig.getGroupId();
    }

    /**
     * configure().
     */
    @Override
    public void configure() {
        from("direct:inner-route")
                .routeId("inner-route")
                .toF(kafkaBrokers, "last-out");
    }
}
