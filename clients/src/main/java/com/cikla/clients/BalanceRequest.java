package com.cikla.clients;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BalanceRequest {
    private Double balance;
    private Integer stock;
    private Integer count;
}
