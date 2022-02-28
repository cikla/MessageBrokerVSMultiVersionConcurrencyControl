package com.cikla.balanceWithMVCC;

import com.cikla.balanceWithMVCC.exception.ConcurrentException;
import com.cikla.balanceWithMVCC.exception.StockException;
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

    public Optional<BalanceWithMVCC> findFirstBalanceSe(){
        Optional<BalanceWithMVCC> optionalBalanceWithRabbitMQ = findFirstBalance();
        return Optional.ofNullable(optionalBalanceWithRabbitMQ
                .orElse(
                        BalanceWithMVCC.builder()
                                .balance(0.0d)
                                .build()));
    }

    public boolean checkStockBeforeSave() {
        Optional<BalanceWithMVCC> balanceWithMVCCOptional = findFirstBalanceSe();
        return (balanceWithMVCCOptional.get().getStock() < 499);
    }
    public void changeBalance(BalanceRequest balanceRequest) throws StockException{
        log.info("BalanceServiceMVCC started with request: {}", balanceRequest);
        Optional<BalanceWithMVCC> balanceWithMVCCOptional = findFirstBalanceSe();

        balanceWithMVCCOptional.get().setBalance(balanceRequest.getBalance()+balanceWithMVCCOptional.get().getBalance());
        balanceWithMVCCOptional.get().setStock(balanceRequest.getStock()+balanceWithMVCCOptional.get().getStock());
        try {
            balanceRepository.save(balanceWithMVCCOptional.get());

        }catch (Exception e){
            log.info("BalanceServiceMVCC OptimisticLocking catched : {}", 1);
            if(checkStockBeforeSave())
                changeBalance(balanceRequest);
            else
                throw new StockException("Stok yok abi");
        }
    }
}
