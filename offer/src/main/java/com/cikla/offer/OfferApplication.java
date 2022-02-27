package com.cikla.offer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.cikla.amqp",
                "com.cikla.offer"
        }
)
@EnableFeignClients(
        basePackages = "com.cikla.clients"
)
public class OfferApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfferApplication.class, args);
    }
}
