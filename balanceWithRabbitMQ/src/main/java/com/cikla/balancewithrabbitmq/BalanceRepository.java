package com.cikla.balancewithrabbitmq;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceWithRabbitMQ, Integer> {
}
