package org.example.ru.vtb.ppcd.example.processor.impl

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*
import org.example.ru.vtb.ppcd.example.model.ExchangeContext
import org.example.ru.vtb.ppcd.example.processor.MessageProcessorFunction
import org.springframework.stereotype.Service
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent

private val logger = KotlinLogging.logger {}

@Service
class NoopMessageProcessor(
    private val objectMapper: ObjectMapper
) : MessageProcessorFunction<CreateProductRequestInnerEvent, CreateProductRequestInnerEvent> {
    override fun processMessage(
        exchangeContext:
        ExchangeContext<CreateProductRequestInnerEvent>
    ): CreateProductRequestInnerEvent {
        logger.info { "NoopMessageProcessor.exchangeContext - $exchangeContext, operationUUID - ${UUID.randomUUID()}" }
        return exchangeContext.message
    }

    override fun processClass() = CreateProductRequestInnerEvent::class.java

    override fun getObjectMapper() = objectMapper
}