package com.example.tradetable.mvc;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.Listing;
import com.example.tradetable.entity.TradeOffer;
import com.example.tradetable.entity.WishlistItem;
import com.example.tradetable.repository.CustomerRepository;
import com.example.tradetable.repository.ListingRepository;
import com.example.tradetable.repository.TradeOfferRepository;
import com.example.tradetable.repository.WishlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerMvcController {

    private final CustomerRepository customers;
    private final WishlistItemRepository wishlistItems;
    private final TradeOfferRepository tradeOffers;
    private final ListingRepository listings;

    // ---------------- Session helper ----------------
    private Long currentCustomerId(HttpSession session) {
        Object id = session.getAttribute("customerId");
        if (id == null) {
            // If this happens, user is not logged in via /customers/login
            throw new IllegalStateException("No current customer in session. Please log in first.");
        }
        return (Long) id;
    }

    // ---------------- Dashboard ----------------
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Long customerId = currentCustomerId(session);

        Customer me = customers.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Customer not found."));

        long wishCount = wishlistItems.countByCustomer_Id(me.getId());
        long offerCount = tradeOffers.countByBuyer_Id(me.getId());

        model.addAttribute("me", me);
        model.addAttribute("wishCount", wishCount);
        model.addAttribute("offerCount", offerCount);
        return "customer/dashboard";
    }

    // ---------------- Wishlist ----------------
    @GetMapping("/wishlist")
    public String wishlist(Model model, HttpSession session) {
        List<WishlistItem> items =
                wishlistItems.findAllByCustomer_IdOrderByCreatedAtDesc(currentCustomerId(session));
        model.addAttribute("items", items);
        return "customer/wishlist";
    }

    // ---------------- Add by listingId ----------------
    @PostMapping("/wishlist/add")
    public String addToWishlist(@RequestParam Long listingId,
                                RedirectAttributes ra,
                                HttpSession session) {
        Long customerId = currentCustomerId(session);

        // avoid duplicates
        if (wishlistItems.existsByCustomer_IdAndListing_Id(customerId, listingId)) {
            ra.addFlashAttribute("msg", "That listing is already in your wishlist.");
            return "redirect:/customer/wishlist";
        }

        // Verify listing exists
        Listing listing = listings.findById(listingId)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found: " + listingId));

        WishlistItem wi = new WishlistItem();

        // Set the customer to the current Customer
        customers.findById(customerId).ifPresent(wi::setCustomer);

        wi.setListing(listing);
        wishlistItems.save(wi);

        ra.addFlashAttribute("msg", "Added to wishlist.");
        return "redirect:/customer/wishlist";
    }

    // ---------------- Remove by ID ----------------
    @PostMapping("/wishlist/remove")
    public String removeFromWishlist(@RequestParam Long itemId,
                                     RedirectAttributes ra) {
        wishlistItems.deleteById(itemId);
        ra.addFlashAttribute("msg", "Removed from wishlist.");
        return "redirect:/customer/wishlist";
    }

    // ---------------- Remove by pair (customerId, listingId) ----------------
    @PostMapping("/wishlist/remove-by")
    public String removeFromWishlistByPair(@RequestParam Long listingId,
                                           RedirectAttributes ra,
                                           HttpSession session) {
        wishlistItems.deleteByCustomer_IdAndListing_Id(currentCustomerId(session), listingId);
        ra.addFlashAttribute("msg", "Removed from wishlist.");
        return "redirect:/customer/wishlist";
    }

    // ---------------- Offers ----------------
    @GetMapping("/offers")
    public String myOffers(Model model, HttpSession session) {
        List<TradeOffer> mine =
                tradeOffers.findAllByBuyer_IdOrderByCreatedAtDesc(currentCustomerId(session));
        model.addAttribute("offers", mine);
        return "customer/customer-offers";
    }

    @PostMapping("/offers/create")
    public String createOffer(@RequestParam Long listingId,
                              @RequestParam Integer amountCents,
                              RedirectAttributes ra,
                              HttpSession session) {

        if (amountCents == null || amountCents <= 0) {
            ra.addFlashAttribute("offerError", "Offer must be a positive amount (in cents).");
            return "redirect:/customer/offers";
        }

        Listing listing = listings.findById(listingId)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found: " + listingId));

        TradeOffer offer = new TradeOffer();

        // Set the buyer to the current Customer
        customers.findById(currentCustomerId(session)).ifPresent(offer::setBuyer);

        // Seller is the listing's provider
        offer.setSeller(listing.getProvider());
        offer.setListing(listing);
        offer.setAmountCents(amountCents);

        // Status is an enum, and @PrePersist defaults to PENDING,
        // but setting it explicitly is fine:
        offer.setStatus(TradeOffer.Status.PENDING);

        tradeOffers.save(offer);

        ra.addFlashAttribute("offerMessage", "Offer created.");
        return "redirect:/customer/offers";
    }

    @PostMapping("/offers/{id}/withdraw")
    public String withdrawOffer(@PathVariable Long id,
                                RedirectAttributes ra,
                                HttpSession session) {

        Long customerId = currentCustomerId(session);

        TradeOffer offer = tradeOffers.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found: " + id));

        // Make sure the current user owns this offer
        if (offer.getBuyer() == null || !offer.getBuyer().getId().equals(customerId)) {
            ra.addFlashAttribute("offerError", "You can only withdraw your own offers.");
            return "redirect:/customer/offers";
        }

        // Only allow withdrawing pending offers
        if (offer.getStatus() != null && offer.getStatus() != TradeOffer.Status.PENDING) {
            ra.addFlashAttribute("offerError", "Only pending offers can be withdrawn.");
            return "redirect:/customer/offers";
        }

        offer.setStatus(TradeOffer.Status.WITHDRAWN);
        tradeOffers.save(offer);

        ra.addFlashAttribute("offerMessage", "Offer withdrawn.");
        return "redirect:/customer/offers";
    }

    // ---------------- Browse Listings ----------------
    @GetMapping("/browse")
    public String browse(@RequestParam(required = false) String city,
                         @RequestParam(required = false) String condition,
                         @RequestParam(required = false) String grade,
                         Model model) {
        List<Listing> listingsList;

        if (StringUtils.hasText(condition)) {
            listingsList = listings.findByConditionContaining(condition);
        } else if (StringUtils.hasText(grade)) {
            listingsList = listings.findByGradeContaining(grade);
        } else {
            listingsList = listings.findAllAvailableListings();
        }

        model.addAttribute("listings", listingsList);
        return "customer/customer-browse";
    }
}
