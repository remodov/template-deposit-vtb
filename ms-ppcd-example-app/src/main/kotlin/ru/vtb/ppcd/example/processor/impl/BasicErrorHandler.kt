package org.example.ru.vtb.ppcd.example.processor.impl

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.camel.Exchange
import org.example.ru.vtb.ppcd.example.processor.ErrorHandler
import org.springframework.stereotype.Component
import ru.vtb.ppcd.generated.async.model.Error

private val logger = KotlinLogging.logger {}

@Component
class BasicErrorHandler(
    private val objectMapper: ObjectMapper
) : ErrorHandler {
    override fun processMessage(exchange: Exchange, exception: Exception) {
        Error(
            message = exception.message,
            code = Error.Code.INTERNAL_SERVER_ERROR
        ).apply {
            val outcomeMessage = objectMapper.writeValueAsString(this);
            exchange.`in`.body = outcomeMessage

            logger.error {
                "BasicErrorHandler.processMessage: произошла ошибка - " +
                        "${exchange.getMessage()}, ex - $exception"
            }
        }
    }
}