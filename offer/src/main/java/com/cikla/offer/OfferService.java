package com.cikla.offer;

import com.cikla.amqp.RabbitMQMessageProducer;
import com.cikla.clients.BalanceRequest;
import com.cikla.clients.BalanceWithMVCCClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class OfferService {

    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final BalanceWithMVCCClient balanceWithMVCCClient;

    public void approveOfferWithMVCC (Double amount){
        BalanceRequest balanceRequest = new BalanceRequest().builder().balance(amount).build();
        balanceWithMVCCClient.changeBalance(balanceRequest);
    }

    public void approveOffer (Double amount){
        BalanceRequest balanceRequest = new BalanceRequest().builder().balance(amount).build();
        rabbitMQMessageProducer.publish(balanceRequest,
                "internal.exchange",
                "internal.balance.routing-key");
    }

    public double randomDoubleGenerator(){
        Random r = new Random();
        double rangeMin = -100;
        double rangeMax = 100;
      //  return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return 1;
    }
}
