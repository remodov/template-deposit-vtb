package ru.vtb.ppcd.example.processor.impl;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.vtb.ppcd.example.entity.RequestEntity;
import ru.vtb.ppcd.example.repository.RequestRepository;
import ru.vtb.ppcd.example.utils.KafkaAdminClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class OutboxProcessorExample implements Processor {
    private final RequestRepository requestRepository;
    private final KafkaAdminClient adminClient;

    @Produce("${application.routes.first-in-last-out-id.out}")
    private final ProducerTemplate producerTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getMessage();

        RequestEntity requestEntity = RequestEntity.builder()
            .requestId(UUID.fromString(message.getMessageId()))
            .sum(message.getBody().getSum())
            .initialDate(message.getMessageTimestamp())
            .build();

        requestRepository.save(requestEntity);
    }

    @Scheduled(fixedRate = 60_000)
    public void deadLetterScheduler() {
        if (adminClient.verifyConnection()) {
            Iterable<RequestEntity> messages = requestRepository.findAll();

            messages.iterator().forEachRemaining(message -> {
                producerTemplate.sendBody(message);
                requestRepository.delete(message);
            });
        }
    }
}
