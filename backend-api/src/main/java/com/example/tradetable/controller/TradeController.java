package com.example.tradetable.controller;

import com.example.tradetable.entity.TradeOffer;
import com.example.tradetable.service.TradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService service;
    public TradeController(TradeService service) { this.service = service; }
    
    // Create trade offer: send TradeOffer JSON with {listingId, buyer:{id}, seller:{id}, offeredValueCents}
    @PostMapping("/offers")
    public TradeOffer createOffer(@RequestBody TradeOffer body) {
        return service.createOffer(body);
    }

    @GetMapping("/offers/buyer/{buyerId}")
    public List<TradeOffer> byBuyer(@PathVariable Long buyerId) {
        return service.offersByBuyer(buyerId);
    }

    @GetMapping("/offers/seller/{sellerId}")
    public List<TradeOffer> forSeller(@PathVariable Long sellerId) {
        return service.offersForSeller(sellerId);
    }

    @PostMapping("/offers/{offerId}/accept")
    public TradeOffer accept(@PathVariable Long offerId) {
        return service.setStatus(offerId, TradeOffer.Status.ACCEPTED);
    }
    @PostMapping("/offers/{offerId}/decline")
    public TradeOffer decline(@PathVariable Long offerId) {
        return service.setStatus(offerId, TradeOffer.Status.DECLINED);
    }
    @PostMapping("/offers/{offerId}/cancel")
    public TradeOffer cancel(@PathVariable Long offerId) {
        return service.setStatus(offerId, TradeOffer.Status.CANCELLED);
    }
}
