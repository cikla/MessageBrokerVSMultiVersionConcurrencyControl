package com.cikla.balancewithrabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.cikla.clients.BalanceRequest;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public Optional<BalanceWithRabbitMQ> findFirstBalance(){
        return balanceRepository.findById(1);
    }

    public void changeBalance(BalanceRequest balanceRequest){
        Optional<BalanceWithRabbitMQ> optionalBalanceWithRabbitMQ = findFirstBalance();
        BalanceWithRabbitMQ balanceWithRabbitMQ = optionalBalanceWithRabbitMQ
                .orElse(
                        BalanceWithRabbitMQ.builder()
                        .balance(0.0d)
                        .build());
        balanceWithRabbitMQ.setBalance(balanceRequest.getBalance()+balanceWithRabbitMQ.getBalance());
        balanceRepository.save(balanceWithRabbitMQ);
    }
}
