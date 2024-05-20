package com.example.deposit.config;


import java.util.List;
import java.util.Map;

public record RoutePathWithId(RouteId routeId, String in, List<Map<String, String>> out) {
}
