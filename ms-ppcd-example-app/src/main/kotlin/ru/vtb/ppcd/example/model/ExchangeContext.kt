package org.example.ru.vtb.ppcd.example.model

data class ExchangeContext<T>(
    val message: T,
    val headers: MutableMap<String, Any>,
    val messageId: String
)