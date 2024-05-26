package org.example.ru.vtb.ppcd.example.processor

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.example.ru.vtb.ppcd.example.model.ExchangeContext
import org.example.ru.vtb.ppcd.example.utils.ExchangeUtils.Companion.toExchangeContext
import org.example.ru.vtb.ppcd.example.utils.ExchangeUtils.Companion.updateExchange

interface MessageProcessorFunction<T, R> : Processor {
    override fun process(exchange: Exchange) : Unit =
        toExchangeContext(exchange, getObjectMapper(), processClass())
            .let { exchangeContext ->
                run {
                    ExchangeContext(
                        message = processMessage(exchangeContext),
                        messageId = exchangeContext.messageId,
                        headers = exchangeContext.headers
                    ).let { updatedContext ->
                        updateExchange(exchange, getObjectMapper(), updatedContext)
                    }
                }
            }

    fun processMessage(exchangeContext: ExchangeContext<T>): R

    fun processClass(): Class<T>

    fun getObjectMapper(): ObjectMapper
}