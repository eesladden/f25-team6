// Package declaration must match your source folder structure: src/main/java/com/example/tradetable/mvc
package com.example.tradetable.mvc;

// Import Spring MVC annotations and types used for controllers and views
import org.springframework.stereotype.Controller;        // Marks this class as an MVC controller that returns views (templates)
import org.springframework.ui.Model;                  // Model lets us pass data to FreeMarker templates
import org.springframework.web.bind.annotation.*;     // Provides @RequestMapping, @GetMapping, @PostMapping, @RequestParam

// Import your domain entities (adjust names if your entities differ)
import com.example.tradetable.entity.Customer;        // Represents a customer in the system
import com.example.tradetable.entity.WishlistItem;    // Represents an item on a customer's wishlist
import com.example.tradetable.entity.TradeOffer;      // Represents an offer a customer makes on a listing
import com.example.tradetable.entity.Listing;         // Represents a tradable listing (e.g., a card)

// Import your repositories (adjust names if your repository interfaces differ)
import com.example.tradetable.repository.CustomerRepository;     // JPA repository for Customer
import com.example.tradetable.repository.WishlistRepository;     // JPA repository for WishlistItem
import com.example.tradetable.repository.TradeOfferRepository;   // JPA repository for TradeOffer
import com.example.tradetable.repository.ListingRepository;      // JPA repository for Listing

// Import Java utilities for lists and optional handling
import java.util.List;                               // For returning lists to the view
import java.util.Optional;                           // For safely handling optional results from repositories

/**
 * CustomerMvcController
 * ---------------------
 * This MVC controller returns FreeMarker views under /templates/customer/*.ftl.
 * It keeps REST controllers intact elsewhere, and focuses on server-side rendered pages.
 * Routes are scoped under "/customer" to keep Customer and Provider spaces separate.
 */
@Controller                                       // Tells Spring this class returns views (not JSON) by default
@RequestMapping("/customer")                       // Base path for all routes in this controller
public class CustomerMvcController {

    // === Dependencies (repositories or services) ==============================================
    // Use constructor injection (preferred by Spring) to get required dependencies.

    private final CustomerRepository customerRepository;     // Access to customers
    private final WishlistRepository wishlistRepository;     // Access to wishlist items
    private final TradeOfferRepository tradeOfferRepository; // Access to trade offers
    private final ListingRepository listingRepository;       // Access to listings

    /**
     * Constructor injection for repositories. Spring will autowire these for us.
     * If you prefer @Autowired, you can annotate the constructor or fields, but
     * explicit constructor injection is cleaner and test-friendly.
     */
    public CustomerMvcController(CustomerRepository customerRepository,
                                 WishlistRepository wishlistRepository,
                                 TradeOfferRepository tradeOfferRepository,
                                 ListingRepository listingRepository) {
        this.customerRepository = customerRepository;       // Save injected CustomerRepository
        this.wishlistRepository = wishlistRepository;       // Save injected WishlistRepository
        this.tradeOfferRepository = tradeOfferRepository;   // Save injected TradeOfferRepository
        this.listingRepository = listingRepository;         // Save injected ListingRepository
    }

    // === Helper: current customer (stub) =======================================================
    // For demo purposes we hardcode a "logged-in" customer id. Replace with real auth later.

    /**
     * Returns the current customer's id.
     * In a real app, this would come from the authenticated session/principal.
     */
    private Long currentCustomerId() {
        return 1L; // TODO: replace with real session user id
    }

    // === Dashboard ============================================================================
    // GET /customer/dashboard  -> renders templates/customer/dashboard.ftl

    /**
     * Renders the Customer dashboard with quick stats.
     * @param model The model to pass data to the view.
     * @return the FreeMarker template path "customer/dashboard".
     */
    @GetMapping("/dashboard")                                      // Maps GET requests for /customer/dashboard
    public String dashboard(Model model) {                         // Model holds attributes visible in the template
        // Fetch the current customer; throw if not found (okay for demo)
        Customer me = customerRepository.findById(currentCustomerId())
                .orElseThrow(() -> new IllegalStateException("Demo customer not found (id=1)"));

        // Count wishlist items for this customer (assumes method exists; add if needed)
        long wishCount = wishlistRepository.countByCustomerId(me.getId());

        // Count trade offers for this customer (assumes method exists; add if needed)
        long offerCount = tradeOfferRepository.countByCustomerId(me.getId());

        // Expose data to the template
        model.addAttribute("me", me);                    // Customer object for greeting, etc.
        model.addAttribute("wishCount", wishCount);      // Number of wishlist items
        model.addAttribute("offerCount", offerCount);    // Number of offers

        // Return the FreeMarker view located at templates/customer/dashboard.ftl
        return "customer/dashboard";
    }

    // === Wishlist pages =======================================================================
    // GET /customer/wishlist     -> list page
    // POST /customer/wishlist/add    (listingId) -> add item
    // POST /customer/wishlist/remove (itemId)    -> remove item

    /**
     * Shows the customer's wishlist.
     * @param model The model with wishlist items.
     * @return the "customer/wishlist" template.
     */
    @GetMapping("/wishlist")                                           // Maps GET requests to /customer/wishlist
    public String wishlist(Model model) {
        // Fetch all wishlist items for the current customer
        List<WishlistItem> items = wishlistRepository.findAllByCustomerId(currentCustomerId());

        // Pass items to the template for rendering
        model.addAttribute("items", items);

        // Return templates/customer/wishlist.ftl
        return "customer/wishlist";
    }

    /**
     * Adds a listing to the customer's wishlist.
     * Expects a form field named "listingId".
     * @param listingId The id of the listing to add.
     * @return redirect back to the wishlist view.
     */
    @PostMapping("/wishlist/add")                                      // Maps POST /customer/wishlist/add
    public String addToWishlist(@RequestParam("listingId") Long listingId) {
        // Look up the listing; fail fast for demo if not found
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found: " + listingId));

        // Create a new wishlist item and set required fields
        WishlistItem wi = new WishlistItem();           // Construct a new WishlistItem entity
        wi.setCustomerId(currentCustomerId());          // Associate with current customer (demo id)
        wi.setListing(listing);                         // Link to the target listing

        // Save the wishlist item
        wishlistRepository.save(wi);

        // Redirect avoids duplicate form submissions and refreshes the list
        return "redirect:/customer/wishlist";
    }

    /**
     * Removes a wishlist item.
     * Expects a form field named "itemId".
     * @param itemId The wishlist item id to remove.
     * @return redirect back to the wishlist view.
     */
    @PostMapping("/wishlist/remove")                                  // Maps POST /customer/wishlist/remove
    public String removeFromWishlist(@RequestParam("itemId") Long itemId) {
        // Delete by id (no-op if already deleted)
        wishlistRepository.deleteById(itemId);

        // Redirect back to the wishlist view
        return "redirect:/customer/wishlist";
    }

    // === Trade Offers pages ===================================================================
    // GET /customer/offers       -> list page
    // POST /customer/offers/create (listingId, amountCents) -> create offer

    /**
     * Shows offers created by the current customer, newest first.
     * @param model The model with offers list.
     * @return the "customer/offers" template.
     */
    @GetMapping("/offers")                                             // Maps GET /customer/offers
    public String myOffers(Model model) {
        // Retrieve offers for this customer (order by createdAt desc if method exists)
        List<TradeOffer> mine = tradeOfferRepository
                .findAllByCustomerIdOrderByCreatedAtDesc(currentCustomerId());

        // Pass to the view
        model.addAttribute("offers", mine);

        // Return templates/customer/offers.ftl
        return "customer/offers";
    }

    /**
     * Creates a new offer for a listing.
     * Expects form fields "listingId" and "amountCents".
     * @param listingId The target listing id.
     * @param amountCents The offer amount in cents (integer).
     * @return redirect back to the offers list.
     */
    @PostMapping("/offers/create")                                     // Maps POST /customer/offers/create
    public String createOffer(@RequestParam("listingId") Long listingId,
                              @RequestParam("amountCents") Integer amountCents) {
        // Validate target listing exists
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found: " + listingId));

        // Create new TradeOffer entity and populate fields
        TradeOffer offer = new TradeOffer();             // Construct new offer
        offer.setCustomerId(currentCustomerId());        // Associate with current customer
        offer.setListing(listing);                       // Reference the listing being offered on
        offer.setAmountCents(amountCents);               // Amount entered in the form (in cents)

        // If your TradeOffer has an enum Status, set default to PENDING (adjust to your enum)
        // Example: TradeOffer.Status.PENDING
        try {
            // Only call this if your entity actually has a Status enum
            // comment out if not present in your model yet
            TradeOffer.Status defaultStatus = TradeOffer.Status.PENDING;
            offer.setStatus(defaultStatus);
        } catch (Exception ignored) {
            // If there's no Status field/enum yet, ignore (keeps this controller reusable)
        }

        // Save the new offer
        tradeOfferRepository.save(offer);

        // Redirect back to the offers page to show the updated list
        return "redirect:/customer/offers";
    }
}
