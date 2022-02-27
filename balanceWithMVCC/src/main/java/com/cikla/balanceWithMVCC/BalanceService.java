package com.cikla.balanceWithMVCC;

import com.cikla.clients.BalanceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public Optional<BalanceWithMVCC> findFirstBalance(){
        return balanceRepository.findById(1);
    }

    public void changeBalance(BalanceRequest balanceRequest){
        log.info("BalanceServiceMVCC started with request: {}", balanceRequest);
        Optional<BalanceWithMVCC> optionalBalanceWithRabbitMQ = findFirstBalance();
        BalanceWithMVCC balanceWithMVCC = optionalBalanceWithRabbitMQ
                .orElse(
                        BalanceWithMVCC.builder()
                        .balance(0.0d)
                        .build());
        balanceWithMVCC.setBalance(balanceRequest.getBalance()+balanceWithMVCC.getBalance());
        try {
            balanceRepository.save(balanceWithMVCC);
        }catch (Exception e){
            log.info("BalanceServiceMVCC OptimisticLocking catched : {}", 1);
        }
    }
}
