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
    
    public WishlistItem add(Long customerId, WishlistItem item) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        item.setCustomer(customer);
        return repo.save(item);
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
