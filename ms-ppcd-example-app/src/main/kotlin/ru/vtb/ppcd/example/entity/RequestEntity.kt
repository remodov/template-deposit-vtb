package org.example.ru.vtb.ppcd.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "request")
data class RequestEntity(
    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    var recordId: UUID? = null,

    @Column(name = "request_id")
    var requestId: UUID? = null,

    @Column(name = "sum")
    var sum: BigDecimal? = null,

    @Column(name = "initial_date")
    var initialDate: OffsetDateTime? = null
)