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
    InnerRoute innerRoute;

    @Test
    void shouldValidSetUp() throws NoSuchFieldException, IllegalAccessException {
        Class<?> myClass = innerRoute.getClass();
        Field field = myClass.getDeclaredField("kafkaBrokers");
        field.setAccessible(true);
        Object value = field.get(innerRoute);
        assertNotNull(value);
    }

    @Test
    void shouldValidConfigure() {
        assertDoesNotThrow(() -> innerRoute.configure());
    }
}