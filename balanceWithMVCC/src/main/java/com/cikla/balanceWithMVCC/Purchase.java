package com.cikla.balanceWithMVCC;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Purchase {
    @Id
    @SequenceGenerator(
            name = "purchase_id_sequence",
            sequenceName = "purchase_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_id_sequence"
    )
    private Integer purcahseId;

    private Integer stockCount;
}
