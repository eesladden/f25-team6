package com.example.tradetable.controller;

import com.example.tradetable.entity.Card;
import com.example.tradetable.entity.Listing;
import com.example.tradetable.entity.Provider;
import com.example.tradetable.service.CardService;
import com.example.tradetable.service.ProviderService;
import com.example.tradetable.service.ListingService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class ProviderMVCController {
    private final ProviderService providerService;
    private final CardService cardService;
    private final ListingService listingService;

    public ProviderMVCController(ProviderService providerService, CardService cardService, ListingService listingService) {
        this.providerService = providerService;
        this.cardService = cardService;
        this.listingService = listingService;
    }

    //signup
    @PostMapping("/providers/signup")
    public Object signUp(Provider provider, @RequestParam MultipartFile imageFile) {
        providerService.createProvider(provider, imageFile);
        return "redirect:/providers/login";
    }
    //login using authenticate method
    @PostMapping("/providers/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        try {
            Provider provider = providerService.authenticate(username, password);
            session.setAttribute("providerId", provider.getId());
            return "redirect:/providers/profile";
        } catch (Exception e) {
            return "redirect:/providers/login?error=true";
        }
    }
    //logout
    @PostMapping("/providers/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/providers/login";
    }
    //updateProviderPassword using session
    @PostMapping("/providers/profile/change-password")
    public String updateProviderPassword(@RequestParam String currentPassword,
                                        @RequestParam String newPassword,
                                        HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        try {
            providerService.updateProviderPassword(providerId, currentPassword, newPassword);
            return "redirect:/providers/profile?passwordChanged=true";
        } catch (Exception e) {
            return "redirect:/providers/profile/change-password?error=true";
        }
    }
    //update provider
    @PostMapping("/providers/profile/update")
    public String updateProvider(Provider provider, @RequestParam MultipartFile imageFile, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        providerService.updateProvider(providerId, provider, imageFile);
        return "redirect:/providers/profile";
    }
    //view change password page
    @GetMapping("/providers/profile/change-password")
    public String viewChangePassword(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "change-provider-password";
    }
    //view profile
    @GetMapping("/providers/profile")
    public String viewProfile(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "provider-profile";
    }
    //view edit profile page
    @GetMapping("/providers/profile/edit")
    public String editProfilePage(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "edit-provider-profile";
    }
    //view provider dashboard
    @GetMapping("/providers/dashboard")
    public String viewDashboard(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "provider-dashboard";
    }
    //view provider login page
    @GetMapping("/providers/login")
    public String viewLoginPage() {
        return "provider-login";
    }
    //view provider signup page
    @GetMapping("/providers/signup")
    public String viewSignupPage() {
        return "provider-signup";
    }

    //CARD STARTS HERE

    //Get all cards
    @GetMapping("/cards")
    public String getAllCards(Model model) {
        model.addAttribute("cards", cardService.getAllCards());
        return "card-list";
    }
    //Get all cards for a specific provider
    @GetMapping("/cards/collection")
    public String getCardsByProvider(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        model.addAttribute("cards", cardService.getCardsByProvider(providerId));
        return "card-collection";
    }
    //Get a card by ID
    @GetMapping("/cards/view/{cardId}")
    public String getCardById(@PathVariable Long cardId, Model model) {
        model.addAttribute("card", cardService.getCardById(cardId));
        return "card-details";
    }
    //View form to edit an existing card
    @GetMapping("/cards/{cardId}/edit")
    public String editCardForm(@PathVariable Long cardId, Model model) {
        model.addAttribute("card", cardService.getCardById(cardId));
        return "card-edit";
    }
    //View form to create a new card
    @GetMapping("/cards/new")
    public String newCardForm(Model model) {
        model.addAttribute("card", new Card());
        return "card-new";
    }
    //Create a new card
    @PostMapping("/cards")
    public Object createCard(Card card, @RequestParam MultipartFile imageFile, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        card.getProviders().add(provider);
        cardService.createCard(card, imageFile);
        return "redirect:/cards/view/" + card.getId();
    }
    //Update an existing card
    @PostMapping("/cards/{cardId}/update")
    public String updateCard(@PathVariable Long cardId, Card card, @RequestParam MultipartFile imageFile) {
        cardService.updateCard(cardId, card, imageFile);
        return "redirect:/cards/view/" + cardId;
    }
    //Add card to provider's collection
    @PostMapping("/cards/{cardId}/addToProvider")
    public String addCardToProvider(@PathVariable Long cardId,
                                  HttpSession session,
                                  Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        cardService.addCardToProviderCollection(cardId, providerId);
        return "redirect:/cards/collection";
    }
    //Remove card from provider's collection
    @PostMapping("/cards/{cardId}/removeFromProvider")
    public String removeCardFromProvider(@PathVariable Long cardId,
                                         HttpSession session,
                                         Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        cardService.removeCardFromProviderCollection(cardId, providerId);
        return "redirect:/cards/collection";
    }

    //LISTING STARTS HERE

    // Create a new listing
    @PostMapping("/listings/create")
    public String createListing(Listing listing, HttpSession session, @RequestParam Long cardId) {
        Long providerId = (Long) session.getAttribute("providerId");
        listingService.createListing(listing, providerId, cardId);
        return "redirect:/listings/my-listings";
    }
    // Update an existing listing
    @PostMapping("/listings/{id}/update")
    public String updateListing(@PathVariable Long id, Listing listing) {
        listingService.updateListing(id, listing);
        return "redirect:/listings/my-listings";
    }
    //Delete listing
    @PostMapping("/listings/{id}/delete")
    public String deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
        return "redirect:/listings/my-listings";
    }
    //View provider's listings
    @GetMapping("/listings/my-listings")
    public String viewMyListings(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        model.addAttribute("listings", listingService.getListingsByProvider(providerId));
        return "my-listings";
    }
    //View available listings
    @GetMapping("/listings")
    public String viewAvailableListings(Model model) {
        model.addAttribute("listings", listingService.getAllAvailableListings());
        return "listings";
    }
    //View listing details
    @GetMapping("/listings/{id}")
    public String viewListingDetails(@PathVariable Long id, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        return "listing-details";
    }
    //View update listing form
    @GetMapping("/listings/{id}/edit")
    public String showEditListingForm(@PathVariable Long id, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        return "edit-listing";
    }
    //View create listing form
    @GetMapping("/listings/new")
    public String showCreateListingForm(Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        model.addAttribute("cards", cardService.getCardsByProvider(providerId));
        model.addAttribute("listing", new Listing());
        return "create-listing";
    }
}

