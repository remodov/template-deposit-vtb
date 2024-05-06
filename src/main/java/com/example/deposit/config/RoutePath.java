package com.example.deposit.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoutePath {
    private final String in;
    private final String out;
}
