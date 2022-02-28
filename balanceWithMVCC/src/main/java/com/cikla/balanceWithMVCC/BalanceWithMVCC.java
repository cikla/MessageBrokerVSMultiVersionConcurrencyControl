package com.cikla.balanceWithMVCC;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BalanceWithMVCC {
    @Id
    @SequenceGenerator(
            name = "balancewithmvcc_id_sequence",
            sequenceName = "balancewithmvcc_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "balancewithmvcc_id_sequence"
    )
    private Integer balanceWithMVCCId;
    private Double balance;
    private Integer stock;

    @Version
    private Integer version;
}

