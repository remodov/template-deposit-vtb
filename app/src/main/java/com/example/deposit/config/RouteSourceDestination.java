package com.example.deposit.config;

import java.util.List;

public record RouteSourceDestination(String source, List<RouteIdPath> destination) {
}
