package com.cikla.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "balancewithmvcc",url = "http://localhost:8085")
public interface BalanceWithMVCCClient {
    @PostMapping(path = "/api/v1/balancewithmvcc/changeBalance")
    void changeBalance(@RequestBody  BalanceRequest balanceRequest);
}
