package org.example.ru.vtb.ppcd.example.processor.impl

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.UUID
import org.example.ru.vtb.ppcd.example.entity.RequestEntity
import org.example.ru.vtb.ppcd.example.model.ExchangeContext
import org.example.ru.vtb.ppcd.example.processor.MessageProcessor
import org.example.ru.vtb.ppcd.example.repository.RequestRepository
import org.springframework.stereotype.Component
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent


@Component
class BasicMessageProcessor(
    private val objectMapper: ObjectMapper,
    private val requestRepository: RequestRepository
) : MessageProcessor<CreateProductRequestInnerEvent> {

    override fun processMessage(exchangeContext: ExchangeContext<CreateProductRequestInnerEvent>) {
        exchangeContext.message
            .let {
                RequestEntity(
                    initialDate = it.timestamp,
                    sum = it.body.sum,
                    requestId = UUID.fromString(it.id)
                )
            }.apply { requestRepository.save(this) }
    }

    override fun getObjectMapper() = objectMapper

    override fun processClass() = CreateProductRequestInnerEvent::class.java
}