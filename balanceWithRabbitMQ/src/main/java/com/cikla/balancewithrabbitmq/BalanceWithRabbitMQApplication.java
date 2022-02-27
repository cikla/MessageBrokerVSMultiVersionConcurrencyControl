package com.cikla.balancewithrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication(
        scanBasePackages = {
                "com.cikla.balancewithrabbitmq",
                "com.cikla.amqp"
        }
)
public class BalanceWithRabbitMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(BalanceWithRabbitMQApplication.class, args);
    }
}
