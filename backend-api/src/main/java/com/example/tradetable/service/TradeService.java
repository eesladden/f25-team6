package com.example.tradetable.service;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.TradeOffer;
import com.example.tradetable.repository.CustomerRepository;
import com.example.tradetable.repository.TradeOfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TradeService {

    private final TradeOfferRepository repo;
    private final CustomerRepository customerRepo;

    public TradeService(TradeOfferRepository repo, CustomerRepository customerRepo) {
        this.repo = repo; this.customerRepo = customerRepo;
    }

    public TradeOffer createOffer(TradeOffer incoming) {
        if (incoming.getBuyer() == null || incoming.getBuyer().getId() == null)
            throw new IllegalArgumentException("Buyer required");
        if (incoming.getSeller() == null || incoming.getSeller().getId() == null)
            throw new IllegalArgumentException("Seller required");

        Customer buyer = customerRepo.findById(incoming.getBuyer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));
        Customer seller = customerRepo.findById(incoming.getSeller().getId())
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        incoming.setBuyer(buyer);
        incoming.setSeller(seller);
        if (incoming.getStatus() == null) incoming.setStatus(TradeOffer.Status.PENDING);

        return repo.save(incoming);
    }

    @Transactional(readOnly = true)
    public List<TradeOffer> offersByBuyer(Long buyerId) {
        return repo.findByBuyer_Id(buyerId);
    }

    @Transactional(readOnly = true)
    public List<TradeOffer> offersForSeller(Long sellerId) {
        return repo.findBySeller_Id(sellerId);
    }

    public TradeOffer setStatus(Long offerId, TradeOffer.Status status) {
        TradeOffer t = repo.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));
        t.setStatus(status);
        return t;
    }
}
