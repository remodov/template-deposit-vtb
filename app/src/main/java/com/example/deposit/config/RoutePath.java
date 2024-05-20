package com.example.deposit.config;


import java.util.List;
import java.util.Map;

public record RoutePath(String in, List<Map<String, String>> out) {
}
