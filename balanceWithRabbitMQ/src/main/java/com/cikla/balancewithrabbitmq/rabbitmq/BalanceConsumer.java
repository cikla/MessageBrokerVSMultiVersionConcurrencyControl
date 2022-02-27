package com.cikla.balancewithrabbitmq.rabbitmq;

import com.cikla.balancewithrabbitmq.BalanceService;
import com.cikla.clients.BalanceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class BalanceConsumer {

    private final BalanceService balanceService;

    @RabbitListener(queues = "${rabbitmq.queue.balance}")
    public void consumer(BalanceRequest balanceRequest){
        log.info("Consumed {} from queue", balanceRequest);
        balanceService.changeBalance(balanceRequest);
    }
}
