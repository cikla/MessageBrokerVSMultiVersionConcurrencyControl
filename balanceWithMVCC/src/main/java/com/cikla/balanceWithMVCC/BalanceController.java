package com.cikla.balanceWithMVCC;

import com.cikla.clients.BalanceRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/balancewithmvcc")
@AllArgsConstructor
public class BalanceController {

    private BalanceService balanceService;

    @PostMapping("/changeBalance")
    public void changeBalance(@RequestBody BalanceRequest balanceRequest){
        balanceService.changeBalance(balanceRequest);
    }
}
