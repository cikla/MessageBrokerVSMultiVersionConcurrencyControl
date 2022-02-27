package com.cikla.balanceWithMVCC;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceWithMVCC, Integer> {

}
