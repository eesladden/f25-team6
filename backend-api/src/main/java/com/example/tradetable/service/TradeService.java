package com.example.tradetable.service;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.Provider;
import com.example.tradetable.entity.TradeOffer;
import com.example.tradetable.repository.CustomerRepository;
import com.example.tradetable.repository.ProviderRepository;
import com.example.tradetable.repository.TradeOfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class TradeService {

    private final TradeOfferRepository repo;
    private final CustomerRepository customerRepo;
    private final ProviderRepository providerRepo;

    public TradeService(TradeOfferRepository repo,
                        CustomerRepository customerRepo,
                        ProviderRepository providerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
        this.providerRepo = providerRepo;
    }

    // Accepts a TradeOffer JSON with nested { buyer: {id}, seller: {id} }
    public TradeOffer createOffer(TradeOffer incoming) {
        if (incoming.getBuyer() == null || incoming.getBuyer().getId() == null)
            throw new IllegalArgumentException("Buyer required");
        if (incoming.getSeller() == null || incoming.getSeller().getId() == null)
            throw new IllegalArgumentException("Seller required");

        Customer buyer = customerRepo.findById(incoming.getBuyer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));
        Provider seller = providerRepo.findById(incoming.getSeller().getId())
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        incoming.setBuyer(buyer);
        incoming.setSeller(seller);

        if (incoming.getStatus() == null) incoming.setStatus(TradeOffer.Status.PENDING);
        if (incoming.getCreatedAt() == null) incoming.setCreatedAt(Instant.now());
        incoming.setUpdatedAt(Instant.now());

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
        t.setUpdatedAt(Instant.now());
        return repo.save(t);
    }
}
