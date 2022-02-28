package com.cikla.balanceWithMVCC;

import com.cikla.balanceWithMVCC.exception.ConcurrentException;
import com.cikla.balanceWithMVCC.exception.StockException;
import com.cikla.clients.BalanceRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> changeBalance(@RequestBody BalanceRequest balanceRequest){
        try {
            balanceService.changeBalance(balanceRequest);

        } catch (StockException ex) {
            return new ResponseEntity<String>(ex.getMessage().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Başarılı", HttpStatus.OK);
    }
}
