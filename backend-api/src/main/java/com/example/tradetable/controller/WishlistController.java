package com.example.tradetable.controller;

import com.example.tradetable.entity.WishlistItem;
import com.example.tradetable.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers/{customerId}/wishlist")
public class WishlistController {

    private final WishlistService service;
    public WishlistController(WishlistService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishlistItem add(@PathVariable Long customerId, @Valid @RequestBody WishlistItem body) {
        return service.add(customerId, body);
    }

    @GetMapping
    public List<WishlistItem> list(@PathVariable Long customerId) {
        return service.list(customerId);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long customerId, @PathVariable Long itemId) {
        service.remove(customerId, itemId);
    }
}
