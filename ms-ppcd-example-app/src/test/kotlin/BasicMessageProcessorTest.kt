import com.fasterxml.jackson.databind.ObjectMapper
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID
import org.example.ru.vtb.ppcd.example.model.ExchangeContext
import org.example.ru.vtb.ppcd.example.processor.impl.BasicMessageProcessor
import org.example.ru.vtb.ppcd.example.repository.RequestRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEvent
import ru.vtb.ppcd.generated.async.model.CreateProductRequestInnerEventBody

class BasicMessageProcessorTest {
    private val requestRepository: RequestRepository =
        Mockito.mock(RequestRepository::class.java)

    private val basicMessageProcessor =
        BasicMessageProcessor(ObjectMapper(), requestRepository)

    @Test
    fun processMessage() {
        val requestInnerEvent =
            CreateProductRequestInnerEvent(
                id = UUID.randomUUID().toString(),
                timestamp = OffsetDateTime.now(),
                body = CreateProductRequestInnerEventBody(
                    sum = BigDecimal.ONE
                )
            )

        val exchangeContext = ExchangeContext(
            message = requestInnerEvent,
            headers = mutableMapOf(),
            messageId = "uniq_id"
        )

        basicMessageProcessor.processMessage(exchangeContext)
    }

}