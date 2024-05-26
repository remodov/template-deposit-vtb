package org.example.ru.vtb.ppcd.example.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.example.ru.vtb.ppcd.example.model.ExchangeContext

class ExchangeUtils {

    companion object {
        fun <T> toExchangeContext(
            exchange: Exchange,
            objectMapper: ObjectMapper,
            processClass: Class<T>?
        ): ExchangeContext<T> {
            val incomeMessage = exchange.message.body.toString()
            val incomeHeaders = exchange.getIn().headers
            val messageId = exchange.getIn().messageId

            return ExchangeContext(
                message = objectMapper.readValue(incomeMessage, processClass),
                messageId = messageId,
                headers = incomeHeaders
            )
        }

        fun <T> updateExchange(
            exchange: Exchange,
            objectMapper: ObjectMapper,
            exchangeContext: ExchangeContext<T>
        ) {
            objectMapper.writeValueAsString(exchangeContext.message)
                .let { outcomeMessage ->
                    exchange.message.body = outcomeMessage
                }

            val incomeHeaders = exchange.`in`.headers
            incomeHeaders.forEach { (key: String, value: Any) ->
                exchange.`in`.setHeader(key, value)
            }
        }
    }
}