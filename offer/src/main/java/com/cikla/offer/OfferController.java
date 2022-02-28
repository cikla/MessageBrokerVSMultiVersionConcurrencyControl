package com.cikla.offer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("api/v1/offer")
@AllArgsConstructor
@Slf4j
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/approveOffer")
    public void approveOffer(){
        double randomValue = offerService.randomDoubleGenerator();
        log.info("Yeni ApproveOffer isteği bakiye değişikliği: {}", randomValue);
        offerService.approveOffer(randomValue);

    }

    @GetMapping("/approveOfferWithMVCC")
    public void approveOfferWithMVCC(){
        double randomValue = offerService.randomDoubleGenerator();
        log.info("Yeni ApproveOfferWithMVCC isteği bakiye değişikliği: {}", randomValue);
        offerService.approveOfferWithMVCC(randomValue);

    }

    @GetMapping("/approveOfferWithMVCCTEMP")
    public void approveOfferWithMVCCTEMP(){
        double randomValue = offerService.randomDoubleGenerator();
        log.info("Yeni ApproveOfferWithMVCCTEMP isteği bakiye değişikliği: {}", randomValue);
        offerService.approveOfferWithMVCCTEMP(randomValue);

    }
}
