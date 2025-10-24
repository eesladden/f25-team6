package com.example.tradetable.service;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.WishlistItem;
import com.example.tradetable.repository.CustomerRepository;
import com.example.tradetable.repository.WishlistItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WishlistService {

    private final WishlistItemRepository repo;
    private final CustomerRepository customerRepo;

    public WishlistService(WishlistItemRepository repo, CustomerRepository customerRepo) {
        this.repo = repo; this.customerRepo = customerRepo;
    }

    public WishlistItem add(Long customerId, WishlistItem incoming) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        incoming.setCustomer(customer);

        if (incoming.getListingId() != null
                && repo.existsByCustomer_IdAndListingId(customerId, incoming.getListingId())) {
            throw new IllegalArgumentException("Listing already in wishlist");
        }

        return repo.save(incoming);
    }

    @Transactional(readOnly = true)
    public List<WishlistItem> list(Long customerId) {
        return repo.findByCustomer_Id(customerId);
    }

    public void remove(Long customerId, Long itemId) {
        // Optional, ensures item belongs to customer first
        repo.deleteById(itemId);
    }
}
