package org.example.ru.vtb.ppcd.example.model

import org.example.ru.vtb.ppcd.example.model.RouteId

data class RoutePathWithId(
    val routeId: RouteId,
    val `in`: String,
    val out: String?
)