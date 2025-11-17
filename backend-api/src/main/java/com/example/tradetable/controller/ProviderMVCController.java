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
        if(provider.getName() == null || provider.getName().isEmpty() ||
           provider.getUsername() == null || provider.getUsername().isEmpty() ||
           provider.getEmail() == null || provider.getEmail().isEmpty() ||
           provider.getPhoneNumber() == null || provider.getPhoneNumber().isEmpty() ||
           provider.getPassword() == null || provider.getPassword().isEmpty() ||
           provider.getBirthdate() == null || provider.getBirthdate().isEmpty()) {
            return "redirect:/providers/signup?error=empty_fields";
        }
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
        if ((username == null || username.isEmpty()) && (password == null || password.isEmpty())) {
            return "redirect:/providers/login?error=missing_fields";
        } else if(username == null || username.isEmpty()) {
            return "redirect:/providers/login?error=username_required";
        } else if (password == null || password.isEmpty()) {
            return "redirect:/providers/login?error=password_required";
        }
        try {
            Provider provider = providerService.authenticate(username, password);
            session.setAttribute("providerId", provider.getId());
            return "redirect:/providers/profile";
        } catch (Exception e) {
            return "redirect:/providers/login?error=invalid_credentials";
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
     * Delete a provider account
     * @param session the HTTP session
     * @return redirect to the login page
     */
    @PostMapping("/providers/delete-account")
    public String deleteAccount(HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        for(Listing listing : providerService.getProviderById(providerId).getListings()) {
            listingService.deleteListing(listing.getId());
        }
        for(Card card : cardService.getCardsByProvider(providerId)) {
            cardService.removeCardFromProviderCollection(card.getId(), providerId);
        }
        if (providerId != null) {
            providerService.deleteProviderById(providerId);
            session.invalidate();
        }
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
        if(provider.getName() == null || provider.getName().isEmpty() ||
           provider.getUsername() == null || provider.getUsername().isEmpty() ||
           provider.getEmail() == null || provider.getEmail().isEmpty() ||
           provider.getPhoneNumber() == null || provider.getPhoneNumber().isEmpty()) {
            return "redirect:/providers/profile/edit?error=true";
        }
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
    public String viewChangePassword(@RequestParam(required = false) String error, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (error != null) {
            model.addAttribute("changePasswordError", true);
        }
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
    public String editProfilePage(@RequestParam(required = false) String error, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        if (error != null) {
            model.addAttribute("editProfileError", true);
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
        if ("username_required".equals(error)) {
            model.addAttribute("usernameError", "Username is required.");
        }
        else if ("password_required".equals(error)) {
            model.addAttribute("passwordError", "Password is required.");
        }
        else if ("invalid_credentials".equals(error)) {
            model.addAttribute("loginError", "Invalid username or password.");
        } else if ("missing_fields".equals(error)) {
            model.addAttribute("usernameError", "Username is required.");
            model.addAttribute("passwordError", "Password is required.");
        }
        return "provider-login";
    }
    /**
     * View signup page
     * @param error optional error parameter
     * @param model the model
     * @return the provider signup view
     */
    @GetMapping("/providers/signup")
    public String viewSignupPage(@RequestParam(required = false) String error, Model model) {
        if ("empty_fields".equals(error)) {
            model.addAttribute("signupError", true);
        } else if ("password_mismatch".equals(error)) {
            model.addAttribute("passwordMismatchError", true);
        }
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
        java.util.List<Card> allCards = cardService.getAllCards();
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
    public String editCardForm(@RequestParam(required = false) String error, @PathVariable Long cardId, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        if(error != null) {
            model.addAttribute("editError", true);
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
    public String newCardForm(@RequestParam(required = false) String error,HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        if(error != null) {
            model.addAttribute("creationError", true);
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
        if(card.getName() == null || card.getName().isEmpty() ||
           card.getGame() == null || card.getGame().isEmpty() ||
           card.getSet() == null || card.getSet().isEmpty() ||
           card.getRarity() == null || card.getRarity().isEmpty()) {
            return "redirect:/cards/new?error=true";
        }
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
        if(card.getName() == null || card.getName().isEmpty() ||
           card.getGame() == null || card.getGame().isEmpty() ||
           card.getSet() == null || card.getSet().isEmpty() ||
           card.getRarity() == null || card.getRarity().isEmpty()) {
            return "redirect:/cards/" + cardId + "/edit?error=true";
        }
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
        if(listing.getCondition() == null || listing.getCondition().isEmpty() ||
           listing.getGrade() == null || listing.getGrade().isEmpty() ||
           listing.getMarketPrice() == null ||
           listing.getLowPrice() == null ||
           listing.getHighPrice() == null ||
           listing.getTradingFor() == null || listing.getTradingFor().isEmpty() ||
           listing.getLocation() == null || listing.getLocation().isEmpty()) {
            return "redirect:/listings/new/card/" + cardId + "?error=true";
        }
        listing.setProvider(providerService.getProviderById(providerId));
        listing.setCard(cardService.getCardById(cardId));
        listingService.createListing(listing);
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
    public String updateListing(@PathVariable Long id, Listing listing, HttpSession session) {
        if(listing.getCondition() == null || listing.getCondition().isEmpty() ||
           listing.getGrade() == null || listing.getGrade().isEmpty() ||
           listing.getMarketPrice() == null ||
           listing.getLowPrice() == null ||
           listing.getHighPrice() == null ||
           listing.getTradingFor() == null || listing.getTradingFor().isEmpty() ||
           listing.getLocation() == null || listing.getLocation().isEmpty()) {
            return "redirect:/listings/" + id + "/edit?error=true";
        }
        listing.setCard(listingService.getListingById(id).getCard());
        Long providerId = (Long) session.getAttribute("providerId");
        listing.setProvider(providerService.getProviderById(providerId));
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
        if(listingService.getListingById(id).getIsAvailable()) {
            providerService.decrementListingsListed(providerId);
        }
        listingService.deleteListing(id);
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
     * Search provider's listings by card name
     * @param name the name to search for
     * @param session the HTTP session
     * @param model the model
     * @return the view for searched listings
     */
    @GetMapping("/listings/my-listings/search")
    public String searchMyListingsByCardName(@RequestParam String name, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", listingService.searchListingsByCardNameAndProvider(name, providerId));
        return "my-listings";
    }
    /**
     * Filter provider's listings by condition or grade
     * @param condition the condition to filter by
     * @param grade the grade to filter by
     * @param session the HTTP session
     * @param model the model
     * @return the view for filtered listings
     */
    @GetMapping("/listings/my-listings/filter")
    public String filterMyListings(@RequestParam(required = false) String condition,
                                   @RequestParam(required = false) String grade,
                                   @RequestParam(required = false) String game, @RequestParam(required = false) String set,
                                   @RequestParam(required = false) String rarity,
                                   HttpSession session,
                                   Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        java.util.List<Listing> allProviderListings = listingService.getListingsByProvider(providerId);
        java.util.List<Listing> filteredProviderListings = new java.util.ArrayList<>();
        for (Listing listing : allProviderListings) {
            if ((condition != null && condition.equalsIgnoreCase(listing.getCondition())) ||
                (grade != null && grade.equalsIgnoreCase(listing.getGrade())) ||
                (game != null && listing.getCard().getGame().equalsIgnoreCase(game)) ||
                (set != null && listing.getCard().getSet().equalsIgnoreCase(set)) ||
                (rarity != null && listing.getCard().getRarity().equalsIgnoreCase(rarity))) {
                filteredProviderListings.add(listing);
            }
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", filteredProviderListings);
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
     * Search available listings by card name
     * @param name the name to search for
     * @param model the model
     * @param session the HTTP session
     * @return the view for searched listings
     */
    @GetMapping("/listings/search")
    public String searchAvailableListingsByCardName(@RequestParam String name, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", listingService.searchAvailableListingsByCardName(name));;
        return "listings";
    }
    /**
     * Filter available listings by condition or grade
     * @param condition the condition to filter by
     * @param grade the grade to filter by
     * @param model the model
     * @param session the HTTP session
     * @return the view for filtered listings
     */
    @GetMapping("/listings/filter")
    public String filterAvailableListings(@RequestParam(required = false) String condition,
                                          @RequestParam(required = false) String grade,
                                          Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        java.util.List<Listing> allAvailableListings = listingService.getAllAvailableListings();
        java.util.List<Listing> filteredAvailableListings = new java.util.ArrayList<>();
        for (Listing listing : allAvailableListings) {
            if ((condition != null && condition.equalsIgnoreCase(listing.getCondition())) ||
                (grade != null && grade.equalsIgnoreCase(listing.getGrade())) || listing.getIsAvailable()) {
                filteredAvailableListings.add(listing);
            }
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", filteredAvailableListings);
        return "listings";
    }
    /**
     * Sort available listings by market price, high price, or low price
     * @param sortBy the attribute to sort by
     * @param order the order of sorting (asc or desc)
     * @param model the model
     * @param session the HTTP session
     * @return the view for sorted listings
     */
    @GetMapping("/listings/sort")
    public String sortAvailableListings(@RequestParam String sortBy,
                                        @RequestParam String order,
                                        Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        java.util.List<Listing> listings;
        if (sortBy.equals("marketPrice")) {
            if (order.equals("asc")) {
                listings = listingService.getAllAvailableListingsOrderByMarketPriceAsc();
            } else {
                listings = listingService.getAllAvailableListingsOrderByMarketPriceDesc();
            }
        } else if (sortBy.equals("highPrice")) {
            if (order.equals("asc")) {
                listings = listingService.getAllAvailableListingsOrderByHighPriceAsc();
            } else {
                listings = listingService.getAllAvailableListingsOrderByHighPriceDesc();
            }
        } else if (sortBy.equals("lowPrice")) {
            if (order.equals("asc")) {
                listings = listingService.getAllAvailableListingsOrderByLowPriceAsc();
            } else {
                listings = listingService.getAllAvailableListingsOrderByLowPriceDesc();
            }
        } else {
            listings = listingService.getAllAvailableListings();
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", listings);
        return "listings";
    }
    /**
     * Get available listings by most recent
     * @param model the model
     * @param session the HTTP session
     * @return the view for recent listings
     */
    @GetMapping("/listings/recent")
    public String getRecentAvailableListings(Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", listingService.getAllAvailableListingsOrderByMostRecent());
        return "listings";
    }
    /**
     * Get available listings by location
     * @param location the location to filter by
     * @param model the model
     * @param session the HTTP session
     * @return the view for listings by location
     */
    @GetMapping("/listings/location")
    public String getAvailableListingsByLocation(@RequestParam String location, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", listingService.getAllAvailableListingsByLocation(location));
        return "listings";
    }
    /**
     * Get available listings by provider username
     * @param username the provider's username
     * @param model the model
     * @param session the HTTP session
     * @return the view for the provider's listings
     */
    @GetMapping("/listings/provider/{username}")
    public String getAvailableListingsByProviderUsername(@PathVariable String username, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("listings", listingService.getAllAvailableListingsByProviderUsername(username));
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
    public String showEditListingForm(@RequestParam(required = false) String error, @PathVariable Long id, HttpSession session,Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        if(error != null) {
            model.addAttribute("editError", true);
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
    public String showCreateListingForm(@RequestParam(required = false) String error, @PathVariable Long cardId, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/providers/login";
        }
        if(error != null) {
            model.addAttribute("creationError", true);
        }
        model.addAttribute("card", cardService.getCardById(cardId));
        model.addAttribute("listing", new Listing());
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "create-listing";
    }
}

