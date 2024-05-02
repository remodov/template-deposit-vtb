package com.example.deposit.route;

import com.example.deposit.config.KafkaConfig;
import com.example.deposit.service.BasicMessageProcessor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirstInLastOutRoute extends RouteBuilder {
    private final KafkaConfig kafkaConfig;

    private String kafkaBrokers;

    @PostConstruct
    public void setUp() {
        kafkaBrokers = "kafka:%s?brokers=" + kafkaConfig.getInnerBrokers() +
                "&groupId=" + kafkaConfig.getGroupId();
    }


    @Override
    public void configure() {
        fromF(kafkaBrokers, "first-in")
                .log(LoggingLevel.INFO,"офсет - [${header.kafka.OFFSET}], тело - [${body}]")
                .bean(BasicMessageProcessor.class)
                .to("direct:inner-route");
    }
}
