package org.example.ru.vtb.ppcd.example.processor

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.example.ru.vtb.ppcd.example.model.ExchangeContext
import org.example.ru.vtb.ppcd.example.utils.ExchangeUtils.Companion.toExchangeContext
import org.example.ru.vtb.ppcd.example.utils.ExchangeUtils.Companion.updateExchange

interface MessageProcessor<T> : Processor {
    override fun process(exchange: Exchange) {
        toExchangeContext(exchange, getObjectMapper(), processClass())
            .let { exchangeContext ->
                {
                    processMessage(exchangeContext)
                    updateExchange(exchange, getObjectMapper(), exchangeContext)
                }
            }
    }

    fun processMessage(exchangeContext: ExchangeContext<T>)

    fun getObjectMapper(): ObjectMapper

    fun processClass(): Class<T>
}