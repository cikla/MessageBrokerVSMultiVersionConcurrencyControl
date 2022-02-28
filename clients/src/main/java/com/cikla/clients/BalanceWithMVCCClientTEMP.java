package com.cikla.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "balancewithmvcctemp",url = "http://localhost:8086")
public interface BalanceWithMVCCClientTEMP {
    @PostMapping(path = "/api/v1/balancewithmvcc/changeBalance")
    void changeBalance(@RequestBody BalanceRequest balanceRequest);
}