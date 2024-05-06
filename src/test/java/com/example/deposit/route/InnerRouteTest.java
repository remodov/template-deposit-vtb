package com.example.deposit.route;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class InnerRouteTest {
    @Autowired
    InnerRoute innerRoute;

    @Test
    void shouldValidSetUp() {
        assertNotNull(innerRoute.getKafkaBrokers());
    }

    @Test
    void shouldValidConfigure() {
        assertDoesNotThrow(()->innerRoute.configure());
    }
}