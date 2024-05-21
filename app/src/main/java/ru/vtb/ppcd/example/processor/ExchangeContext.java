package ru.vtb.ppcd.example.processor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExchangeContext<T> {
    private T message;
    private Map<String, Object> headers;
    private String messageId;
}
