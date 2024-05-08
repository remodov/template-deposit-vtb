package com.example.deposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan
public class DepositApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepositApplication.class, args);
    }
}
