package com.example.deposit.route;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class InnerRouteTest {
    @Autowired
    InnerRouteTo innerRouteTo;

    @Test
    void shouldValidSetUp() throws NoSuchFieldException, IllegalAccessException {
        Class<?> myClass = innerRouteTo.getClass();
        Field field = myClass.getDeclaredField("kafkaBrokers");
        field.setAccessible(true);
        Object value = field.get(innerRouteTo);
        assertNotNull(value);
    }

    @Test
    void shouldValidConfigure() {
        assertDoesNotThrow(() -> innerRouteTo.configure());
    }
}