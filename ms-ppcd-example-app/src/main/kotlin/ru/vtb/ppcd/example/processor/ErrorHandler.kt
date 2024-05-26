package org.example.ru.vtb.ppcd.example.processor

import org.apache.camel.Exchange
import org.apache.camel.Processor

interface ErrorHandler : Processor {
    override fun process(exchange: Exchange) {
        exchange.getProperty(
            Exchange.EXCEPTION_CAUGHT,
            Exception::class.java
        ).also { caused -> processMessage(exchange, caused) }
    }

    fun processMessage(exchange: Exchange, exception: Exception)
}