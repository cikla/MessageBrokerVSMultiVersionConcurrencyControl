package com.cikla.balancewithrabbitmq;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BalanceWithRabbitMQ {
    @Id
    @SequenceGenerator(
            name = "balancewithrabbitmq_id_sequence",
            sequenceName = "balancewithrabbitmq_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "balancewithrabbitmq_id_sequence"
    )
    private Integer balanceWithRabbitMQId;
    private Double balance;
}
