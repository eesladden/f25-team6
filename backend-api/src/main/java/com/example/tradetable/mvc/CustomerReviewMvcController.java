package com.example.tradetable.mvc;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.Listing;
import com.example.tradetable.entity.Provider;
import com.example.tradetable.entity.Review;
import com.example.tradetable.service.CustomerService;
import com.example.tradetable.service.ListingService;
import com.example.tradetable.service.ProviderService;
import com.example.tradetable.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/reviews")
public class CustomerReviewMvcController {

    private final ReviewService reviewService;
    private final CustomerService customerService;
    private final ProviderService providerService;
    private final ListingService listingService;

    private Long currentCustomerId(HttpSession session) {
        Object id = session.getAttribute("customerId");
        if (id == null) {
            throw new IllegalStateException("No current customer in session. Please log in first.");
        }
        return (Long) id;
    }

    // List reviews written by this customer
    @GetMapping("/my-reviews")
    public String myReviews(HttpSession session, Model model) {
        Long customerId = currentCustomerId(session);
        Customer customer = customerService.get(customerId);

        List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("reviews", reviews);
        return "customer/customer-my-reviews";
    }

    // Show form to write a new review for a provider/listing
    @GetMapping("/new")
    public String newReviewForm(@RequestParam Long providerId,
                                @RequestParam(required = false) Long listingId,
                                HttpSession session,
                                Model model) {
        Long customerId = currentCustomerId(session);

        model.addAttribute("customer", customerService.get(customerId));
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);

        if (listingId != null) {
            Listing listing = listingService.getListingById(listingId);
            model.addAttribute("listing", listing);
        }

        return "customer/customer-review-new";
    }

    // Create review
    @PostMapping
    public String createReview(@RequestParam Long providerId,
                               @RequestParam(required = false) Long listingId,
                               @RequestParam int rating,
                               @RequestParam String comment,
                               @RequestParam(required = false, name = "tags") List<String> tags,
                               HttpSession session,
                               RedirectAttributes ra) {
        Long customerId = currentCustomerId(session);

        reviewService.createReview(customerId, providerId, listingId, rating, comment, tags);

        ra.addFlashAttribute("msg", "Review submitted. Thank you!");
        return "redirect:/customer/reviews/my-reviews";
    }
}
