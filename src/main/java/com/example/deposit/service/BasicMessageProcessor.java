package com.example.deposit.service;

import com.example.deposit.entity.BasicEntity;
import com.example.deposit.repository.BasicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicMessageProcessor implements Processor {
    private final BasicRepository basicRepository;

    @Transactional
    public void processAnotherMessage(String message) {

        BasicEntity basicEntity = BasicEntity.builder()
                .message(message)
                .build();

        basicRepository.save(basicEntity);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        BasicEntity basicEntity = BasicEntity.builder()
                .message(exchange.getMessage().getBody().toString())
                .build();

        basicRepository.save(basicEntity);
    }
}
