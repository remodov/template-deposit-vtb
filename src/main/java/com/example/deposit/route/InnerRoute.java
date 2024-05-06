package com.example.deposit.route;

import com.example.deposit.config.KafkaConfig;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InnerRoute extends RouteBuilder {
    private final KafkaConfig kafkaConfig;
    @Getter
    private String kafkaBrokers;

    @PostConstruct
    public void setUp() {
        kafkaBrokers = "kafka:%s?brokers=" + kafkaConfig.getInnerBrokers() +
                "&groupId=" + kafkaConfig.getGroupId();
    }


    @Override
    public void configure() {
        from("direct:inner-route")
                .log(LoggingLevel.INFO,"офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .toF(kafkaBrokers, "last-out");
    }
}
