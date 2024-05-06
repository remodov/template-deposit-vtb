package com.example.deposit.route;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class FirstInLastOutRouteTest {

    @Value("${topics.public.agreement.request}")
    String aggRequest;

    @Autowired
    FirstInLastOutRoute firstInLastOutRoute;

    @Test
    void shouldValidSetUp() throws NoSuchFieldException, IllegalAccessException {
        Class<?> myClass = firstInLastOutRoute.getClass();
        Field field = myClass.getDeclaredField("kafkaBrokers");
        field.setAccessible(true);
        Object value = field.get(firstInLastOutRoute);
        assertNotNull(value);
    }

    @Test
    void shouldValidConfigure() {
        assertDoesNotThrow(() -> firstInLastOutRoute.configure());
    }
}