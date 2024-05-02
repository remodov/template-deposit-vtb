package com.example.deposit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "basic")
@AllArgsConstructor
@NoArgsConstructor
public class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID recordId;

    private String message;
}
