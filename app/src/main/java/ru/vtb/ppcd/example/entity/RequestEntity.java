package ru.vtb.ppcd.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Builder
@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
public class RequestEntity {
    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID recordId;

    @Column(name = "request_id")
    private UUID requestId;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "initial_date")
    private OffsetDateTime initialDate;
}
