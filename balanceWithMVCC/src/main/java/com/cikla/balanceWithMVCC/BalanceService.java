package com.cikla.balanceWithMVCC;

import com.cikla.balanceWithMVCC.exception.ConcurrentException;
import com.cikla.balanceWithMVCC.exception.StockException;
import com.cikla.clients.BalanceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
@Slf4j
public class BalanceService {

    private final AtomicInteger count = new AtomicInteger();
    private final BalanceRepository balanceRepository;
    private final PurchaseRepository purchaseRepository;

    public Optional<BalanceWithMVCC> findFirstBalance(){
        return balanceRepository.findById(1);
    }

    public Optional<BalanceWithMVCC> findFirstBalanceWithId(){
        Optional<BalanceWithMVCC> optionalBalanceWithRabbitMQ = findFirstBalance();
        return Optional.ofNullable(optionalBalanceWithRabbitMQ
                .orElse(
                        BalanceWithMVCC.builder()
                                .balance(0.0d)
                                .stock(0)
                                .build()));
    }

    public boolean checkStockBeforeSave(Optional<BalanceWithMVCC> balanceWithMVCCOptional) {
        //Optional<BalanceWithMVCC> balanceWithMVCCOptional = findFirstBalanceWithId();
        return (balanceWithMVCCOptional.get().getStock() <= 999);
    }

    public void purchaseProccess(BalanceRequest balanceRequest){
        Purchase purchase = Purchase.builder().stockCount(balanceRequest.getCount()).build();
        purchaseRepository.save(purchase);
    }

    public void changeBalance(BalanceRequest balanceRequest) throws StockException{

        if(balanceRequest.getCount() == null) balanceRequest.setCount(count.incrementAndGet());

        //find one id=1
        Optional<BalanceWithMVCC> balanceWithMVCCOptional = findFirstBalanceWithId();

        //change entity object according to incoming request
        balanceWithMVCCOptional.get().setBalance(balanceRequest.getBalance()+balanceWithMVCCOptional.get().getBalance());
        balanceWithMVCCOptional.get().setStock(balanceRequest.getStock()+balanceWithMVCCOptional.get().getStock());

        try {
            if(checkStockBeforeSave(balanceWithMVCCOptional)) {
                balanceRepository.save(balanceWithMVCCOptional.get());
                purchaseProccess(balanceRequest);
            }
        }catch (Exception e){
            log.info("BalanceServiceMVCC OptimisticLocking caught: {}", e.getMessage());
            if(checkStockBeforeSave(balanceWithMVCCOptional))
                changeBalance(balanceRequest);
            else
                throw new StockException("Stok Kalmadı");
        }
    }
}
