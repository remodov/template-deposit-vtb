package com.example.deposit.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * KafkaConfig.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaConfig {
    String groupId;
    String innerBrokers;
}
