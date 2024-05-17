package com.example.deposit.service;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExchangeContext<T> {
    private T message;
    private Map<String, Object> headers;
    private String messageId;
}
