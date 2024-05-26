package org.example.ru.vtb.ppcd.example.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ObjectMapperConfig {

    @Bean
    open fun objectMapper(): ObjectMapper =
        ObjectMapper()
            .registerModules(JavaTimeModule())
}