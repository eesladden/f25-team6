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

import jakarta.servlet.http.HttpSession;   // <-- added
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerMvcController {

    private final CustomerRepository customers;
    private final WishlistItemRepository wishlistItems;
    private final TradeOfferRepository tradeOffers;
    private final ListingRepository listings;

    // ---------------- Temporary mock login so you can test without real auth ----------------
    @GetMapping("/mock-login")
    public String mockLogin(HttpSession session, RedirectAttributes ra) {
        Long demoId = 1L; // make sure this Customer exists in the DB
        session.setAttribute("customerId", demoId);
        ra.addFlashAttribute("msg", "Mock logged in as customer id=" + demoId);
        return "redirect:/customer/dashboard";
    }

    // TODO: Replace with real session/authenticated user
    private Long currentCustomerId(HttpSession session) {
        Object id = session.getAttribute("customerId");
        if (id == null) {
            throw new IllegalStateException(
                    "No current customer in session. Use /customer/mock-login or implement real login.");
        }
        return (Long) id;
    }

    // ---------------- Dashboard ----------------
    @GetMapping("/dashboard")
    public String dashboard(Model model, RedirectAttributes ra, HttpSession session) {
        Customer me = customers.findById(currentCustomerId(session))
                .orElseThrow(() -> new IllegalStateException("Demo user not found (id=1). Seed a Customer row."));

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

        // Fast path: avoid duplicates
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
        return "customer/offers";
    }

    @PostMapping("/offers/create")
    public String createOffer(@RequestParam Long listingId,
                              @RequestParam Integer amountCents,
                              RedirectAttributes ra,
                              HttpSession session) {
        if (amountCents == null || amountCents <= 0) {
            ra.addFlashAttribute("err", "Offer must be a positive amount (cents).");
            return "redirect:/customer/offers";
        }

        Listing listing = listings.findById(listingId)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found: " + listingId));

        TradeOffer offer = new TradeOffer();
        // Set the buyer to the current Customer
        customers.findById(currentCustomerId(session)).ifPresent(offer::setBuyer);

        offer.setSeller(listing.getProvider());
        offer.setListing(listing);
        offer.setAmountCents(amountCents);

        tradeOffers.save(offer);

        ra.addFlashAttribute("msg", "Offer created.");
        return "redirect:/customer/offers";
    }

    // simple search endpoint to show how you can filter listings from MVC
    @GetMapping("/browse")
    public String browse(@RequestParam(required = false) String city,
                         @RequestParam(required = false) String condition,
                         @RequestParam(required = false) String grade,
                         Model model) {
        List<Listing> results;

        if (StringUtils.hasText(city)) {
            results = listings.findByCityNameContaining(city);
        } else if (StringUtils.hasText(condition)) {
            results = listings.findByConditionContaining(condition);
        } else if (StringUtils.hasText(grade)) {
            results = listings.findByGradeContaining(grade);
        } else {
            results = listings.findAllAvailableListings();
        }

        model.addAttribute("results", results);
        return "customer/browse";
    }
}
