package com.example.deposit.route;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FirstInLastOutRouteTest {

    @Value("${topics.public.agreement.request}")
    String aggRequest;

    @Autowired
    FirstInLastOutRoute firstInLastOutRoute;

    @Test
    void shouldValidSetUp(){
        assertNotNull(firstInLastOutRoute.getKafkaBrokers());
    }

    @Test
    void shouldValidConfigure(){
        assertDoesNotThrow(() -> firstInLastOutRoute.configure());
    }
}