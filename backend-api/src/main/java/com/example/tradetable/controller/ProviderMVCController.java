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
    /**
     * Create a new provider
     * @param provider the provider to create
     * @param imageFile the profile image file
     * @return redirect to the login page
     */
    @PostMapping("/providers/signup")
    public Object signUp(Provider provider, @RequestParam MultipartFile imageFile) {
        providerService.createProvider(provider, imageFile);
        return "redirect:/providers/login";
    }
    /**
     * Login a provider
     * @param username the provider's username
     * @param password the provider's password
     * @param session the HTTP session
     * @return redirect to the provider's profile page if successful, otherwise redirect to the login page with an error
     */
    @PostMapping("/providers/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return "redirect:/providers/login?error=true";
        }
        try {
            Provider provider = providerService.authenticate(username, password);
            session.setAttribute("providerId", provider.getId());
            return "redirect:/providers/profile";
        } catch (Exception e) {
            return "redirect:/providers/login?error=true";
        }
    }
    /**
     * Logout a provider
     * @param session the HTTP session
     * @return redirect to the login page
     */
    @PostMapping("/providers/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/providers/login";
    }
    /**
     * Update the provider's password
     * @param currentPassword the current password
     * @param newPassword the new password
     * @param session the HTTP session
     * @return redirect to the profile page if successful, otherwise redirect to the change password page with an error
     */
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
    /**
     * Update the provider's profile
     * @param provider the provider being updated
     * @param imageFile the profile image file
     * @param session the HTTP session
     * @return redirect to the profile page
     */
    @PostMapping("/providers/profile/update")
    public String updateProvider(Provider provider, @RequestParam MultipartFile imageFile, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        providerService.updateProvider(providerId, provider, imageFile);
        return "redirect:/providers/profile";
    }
    /**
     * View change password page
     * @param session the HTTP session
     * @param model the model
     * @return the change password view
     */
    @GetMapping("/providers/profile/change-password")
    public String viewChangePassword(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "change-provider-password";
    }
    /**
     * View provider profile page
     * @param session the HTTP session
     * @param model the model
     * @return the provider profile view
     */
    @GetMapping("/providers/profile")
    public String viewProfile(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "provider-profile";
    }
    /** 
     * View edit profile page
     * @param session the HTTP session
     * @param model the model
     * @return the edit profile view
     */
    @GetMapping("/providers/profile/edit")
    public String editProfilePage(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "edit-provider-profile";
    }
    /**
     * View provider dashboard
     * @param session the HTTP session
     * @param model the model
     * @return the provider dashboard view
     */
    @GetMapping("/providers/dashboard")
    public String viewDashboard(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "provider-dashboard";
    }
    /**
     * View login page
     * @param error optional error parameter
     * @param model the model
     * @return the provider login view
     */
    @GetMapping("/providers/login")
    public String viewLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && error.equals("true")) {
            model.addAttribute("loginError", true);
        }
        return "provider-login";
    }
    /**
     * View signup page
     * @return the provider signup view
     */
    @GetMapping("/providers/signup")
    public String viewSignupPage() {
        return "provider-signup";
    }

    //CARD STARTS HERE

    /**
     * Get all cards
     * @param model the model
     * @param session the HTTP session
     * @return the card list view
     */
    @GetMapping("/cards")
    public String getAllCards(Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cardService.getAllCards());
        return "card-list";
    }
    /**
     * Search cards by name
     * @param name the name to search for
     * @param model the model
     * @param session the HTTP session
     * @return the card list view
     */
    @GetMapping("/cards/search")
    public String searchCardsByName(@RequestParam String name, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cardService.searchCardsByName(name));
        return "card-list";
    }
    /**
     * Filter cards by game, set, or rarity
     * @param game the game to filter by
     * @param set the set to filter by
     * @param rarity the rarity to filter by
     * @param model the model
     * @param session the HTTP session
     * @return the card list view
     */
    @GetMapping("/cards/filter")
    public String filterCards(@RequestParam(required = false) String game,
                              @RequestParam(required = false) String set,
                              @RequestParam(required = false) String rarity,
                              Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        java.util.List<Card> cards;
        if (game != null) {
            cards = cardService.filterCardsByGame(game);
        } else if (set != null) {
            cards = cardService.filterCardsBySet(set);
        } else if (rarity != null) {
            cards = cardService.filterCardsByRarity(rarity);
        } else {
            cards = cardService.getAllCards();
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cards);
        return "card-list";
    }
    /**
     * Get all cards for a specific provider
     * @param session the HTTP session
     * @param model the model
     * @return the card collection view
     */
    @GetMapping("/cards/collection")
    public String getCardsByProvider(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cardService.getCardsByProvider(providerId));
        return "card-collection";
    }
    /**
     * Search cards by name for a specific provider
     * @param name the name to search for
     * @param session the HTTP session
     * @param model the model
     * @return the card collection view
     */
    @GetMapping("/cards/collection/search")
    public String searchCardsByNameForProvider(@RequestParam String name, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        java.util.List<Card> allCards = cardService.getCardsByProvider(providerId);
        java.util.List<Card> filteredCards = new java.util.ArrayList<>();
        for (Card card : allCards) {
            if (card.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredCards.add(card);
            }
        }
        model.addAttribute("cards", filteredCards);
        return "card-collection";
    }
    /**
     * Filter cards by game, set, or rarity for a specific provider
     * @param game the game to filter by
     * @param set the set to filter by
     * @param rarity the rarity to filter by
     * @param session the HTTP session
     * @param model the model
     * @return the card collection view
     */
    @GetMapping("/cards/collection/filter")
    public String filterCardsForProvider(@RequestParam(required = false) String game,
                                         @RequestParam(required = false) String set,
                                         @RequestParam(required = false) String rarity,
                                         HttpSession session,
                                         Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        java.util.List<Card> allCards = cardService.getCardsByProvider(providerId);
        java.util.List<Card> filteredCards = new java.util.ArrayList<>();
        for (Card card : allCards) {
            if ((game != null && card.getGame().equalsIgnoreCase(game)) ||
                (set != null && card.getSet().equalsIgnoreCase(set)) ||
                (rarity != null && card.getRarity().equalsIgnoreCase(rarity))) {
                filteredCards.add(card);
            }
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", filteredCards);
        return "card-collection";
    }
    /**
     * Get a card by ID
     * @param cardId the ID of the card
     * @param session the HTTP session
     * @param model the model
     * @return the card details view
     */
    @GetMapping("/cards/view/{cardId}")
    public String getCardById(@PathVariable Long cardId, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        model.addAttribute("card", cardService.getCardById(cardId));
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "card-details";
    }
    /**
     * View form to edit an existing card
     * @param cardId the ID of the card
     * @param session the HTTP session
     * @param model the model
     * @return the card edit view
     */
    @GetMapping("/cards/{cardId}/edit")
    public String editCardForm(@PathVariable Long cardId, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        model.addAttribute("card", cardService.getCardById(cardId));
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "card-edit";
    }
    /**
     * View form to create a new card
     * @param session the HTTP session
     * @param model the model
     * @return the card creation view
     */
    @GetMapping("/cards/new")
    public String newCardForm(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        model.addAttribute("card", new Card());
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "card-new";
    }
    /**
     * Create a new card
     * @param card the card to create
     * @param imageFile the image file for the card
     * @param session the HTTP session
     * @return redirect to the card details view
     */
    @PostMapping("/cards")
    public Object createCard(Card card, @RequestParam MultipartFile imageFile, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        card.getProviders().add(provider);
        cardService.createCard(card, imageFile);
        providerService.incrementCollectionSize(providerId);
        return "redirect:/cards/view/" + card.getId();
    }
    /**
     * Update an existing card
     * @param cardId the ID of the card
     * @param card the updated card
     * @param imageFile the image file for the card
     * @return redirect to the card details view
     */
    @PostMapping("/cards/{cardId}/update")
    public String updateCard(@PathVariable Long cardId, Card card, @RequestParam MultipartFile imageFile) {
        cardService.updateCard(cardId, card, imageFile);
        return "redirect:/cards/view/" + cardId;
    }
    /**
     * Add card to provider's collection
     * @param cardId the ID of the card
     * @param session the HTTP session
     * @param model the model
     * @return redirect to the card collection view
     */
    @PostMapping("/cards/{cardId}/addToProvider")
    public String addCardToProvider(@PathVariable Long cardId,
                                  HttpSession session,
                                  Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        cardService.addCardToProviderCollection(cardId, providerId);
        providerService.incrementCollectionSize(providerId);
        return "redirect:/cards/collection";
    }
    /**
     * Remove card from provider's collection
     * @param cardId the ID of the card
     * @param session the HTTP session
     * @param model the model
     * @return redirect to the card collection view
     */
    @PostMapping("/cards/{cardId}/removeFromProvider")
    public String removeCardFromProvider(@PathVariable Long cardId,
                                         HttpSession session,
                                         Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        cardService.removeCardFromProviderCollection(cardId, providerId);
        providerService.decrementCollectionSize(providerId);
        return "redirect:/cards/collection";
    }

    //LISTING STARTS HERE

    /**
     * Create a new listing
     * @param cardId the ID of the card
     * @param listing the listing to create
     * @param session the HTTP session
     * @return redirect to the provider's listings view
     */
    @PostMapping("/listings/create/card/{cardId}")
    public String createListing(@PathVariable Long cardId, Listing listing, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        Listing newListing = new Listing();
        newListing.setProvider(providerService.getProviderById(providerId));
        newListing.setCard(cardService.getCardById(cardId));
        newListing.setGrade(listing.getGrade());
        newListing.setCondition(listing.getCondition());
        newListing.setForSaleOrTrade(listing.getForSaleOrTrade());
        newListing.setMarketPrice(listing.getMarketPrice());
        newListing.setLowPrice(listing.getLowPrice());
        newListing.setHighPrice(listing.getHighPrice());
        newListing.setIsAvailable(listing.getIsAvailable());
        newListing.setTradingFor(listing.getTradingFor());  
        newListing.setLocation(listing.getLocation());
        listingService.createListing(newListing);
        providerService.incrementListingsListed(providerId);
        return "redirect:/listings/my-listings";
    }
    /**
     * Update an existing listing
     * @param id the ID of the listing
     * @param listing the updated listing
     * @return redirect to the provider's listings view
     */
    @PostMapping("/listings/{id}/update")
    public String updateListing(@PathVariable Long id, Listing listing) {
        listingService.updateListing(id, listing);
        return "redirect:/listings/my-listings";
    }
    /**
     * Delete a listing
     * @param id the ID of the listing
     * @return redirect to the provider's listings view
     */
    @PostMapping("/listings/{id}/delete")
    public String deleteListing(@PathVariable Long id, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        listingService.deleteListing(id);
        providerService.decrementListingsListed(providerId);
        return "redirect:/listings/my-listings";
    }
    @PostMapping("/listings/{id}/trade")
    public String trade(@PathVariable Long id, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        listingService.markListingAsUnavailable(id);
        providerService.decrementListingsListed(providerId);
        providerService.incrementTradesCompleted(providerId);
        return "redirect:/listings/my-listings";
    }
    /**
     * View provider's listings
     * @param session the HTTP session
     * @param model the model
     * @return the view for the provider's listings
     */
    @GetMapping("/listings/my-listings")
    public String viewMyListings(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        model.addAttribute("listings", listingService.getListingsByProvider(providerId));
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "my-listings";
    }
    /**
     * View available listings
     * @param model the model
     * @return the view for available listings
     */
    @GetMapping("/listings")
    public String viewAvailableListings(Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", listingService.getAllAvailableListings());
        return "listings";
    }
    /**
     * View listing details
     * @param id the ID of the listing
     * @param session the HTTP session
     * @param model the model
     * @return the view for the listing details
     */
    @GetMapping("/listings/{id}")
    public String viewListingDetails(@PathVariable Long id, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        Boolean availability = listing.getIsAvailable();
        model.addAttribute("availability", availability);
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "listing-details";
    }
    /**
     * View update listing form
     * @param id the ID of the listing
     * @param session the HTTP session
     * @param model the model
     * @return the view for the edit listing form
     */
    @GetMapping("/listings/{id}/edit")
    public String showEditListingForm(@PathVariable Long id, HttpSession session,Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "edit-listing";
    }
    /**
     * View create listing form
     * @param cardId the ID of the card
     * @param model the model
     * @param session the HTTP session
     * @return the view for the create listing form
     */
    @GetMapping("/listings/new/card/{cardId}")
    public String showCreateListingForm(@PathVariable Long cardId, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        model.addAttribute("card", cardService.getCardById(cardId));
        model.addAttribute("listing", new Listing());
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "create-listing";
    }
}

