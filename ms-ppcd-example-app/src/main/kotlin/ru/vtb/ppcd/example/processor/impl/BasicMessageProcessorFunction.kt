package org.example.ru.vtb.ppcd.example.processor.impl

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.ru.vtb.ppcd.example.entity.RequestEntity
import org.example.ru.vtb.ppcd.example.model.ExchangeContext
import org.example.ru.vtb.ppcd.example.processor.MessageProcessorFunction
import org.example.ru.vtb.ppcd.example.repository.RequestRepository
import org.springframework.stereotype.Service
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent
import ru.vtb.ppcd.generated.async.model.CreateProductResponseInnerEvent
import ru.vtb.ppcd.generated.async.model.CreateProductResponseInnerEventBody
import java.util.*

@Service
class BasicMessageProcessorFunction(
    private val requestRepository: RequestRepository,
    private val objectMapper: ObjectMapper
) : MessageProcessorFunction<CreateProductRequestInnerEvent, CreateProductResponseInnerEvent> {

    override fun processMessage(exchangeContext: ExchangeContext<CreateProductRequestInnerEvent>)
            : CreateProductResponseInnerEvent {
        return exchangeContext.message
            .let { message ->
                exchangeContext.headers.also {
                    it["new"] = message.id
                }

                val requestId = UUID.fromString(message.id)

                RequestEntity(
                    initialDate = message.timestamp,
                    sum = message.body.sum,
                    requestId = requestId
                ).let {
                    requestRepository.save(it)
                }.let { requestEntity ->
                    CreateProductResponseInnerEvent(
                        id = requestId,
                        timestamp = message.timestamp,
                        body = CreateProductResponseInnerEventBody(
                            productId = requestEntity.recordId?.toString()
                        )
                    )
                }
            }
    }

    override fun processClass() = CreateProductRequestInnerEvent::class.java

    override fun getObjectMapper() = objectMapper
}