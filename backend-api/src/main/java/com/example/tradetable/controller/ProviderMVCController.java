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

    //CARD STARTS HERE (need to add /providers prefix to routes)

    //Get all cards
    @GetMapping("/cards")
    public String getAllCards(Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cardService.getAllCards());
        return "card-list";
    }
    //Search cards by name
    @GetMapping("/cards/search")
    public String searchCardsByName(@RequestParam String name, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cardService.searchCardsByName(name));
        return "card-list";
    }
    //Filter cards by game, set, or rarity
    @GetMapping("/cards/filter")
    public String filterCards(@RequestParam(required = false) String game,
                              @RequestParam(required = false) String set,
                              @RequestParam(required = false) String rarity,
                              Model model, HttpSession session) {
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
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cards);
        return "card-list";
    }
    //Get all cards for a specific provider
    @GetMapping("/cards/collection")
    public String getCardsByProvider(HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("cards", cardService.getCardsByProvider(providerId));
        return "card-collection";
    }
    //Search cards by name for a specific provider
    @GetMapping("/cards/collection/search")
    public String searchCardsByNameForProvider(@RequestParam String name, HttpSession session, Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
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
    //Filter cards by game, set, or rarity for a specific provider
    @GetMapping("/cards/collection/filter")
    public String filterCardsForProvider(@RequestParam(required = false) String game,
                                         @RequestParam(required = false) String set,
                                         @RequestParam(required = false) String rarity,
                                         HttpSession session,
                                         Model model) {
        Long providerId = (Long) session.getAttribute("providerId");
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
    //Get a card by ID
    @GetMapping("/cards/view/{cardId}")
    public String getCardById(@PathVariable Long cardId, HttpSession session, Model model) {
        model.addAttribute("card", cardService.getCardById(cardId));
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "card-details";
    }
    //View form to edit an existing card
    @GetMapping("/cards/{cardId}/edit")
    public String editCardForm(@PathVariable Long cardId, HttpSession session, Model model) {
        model.addAttribute("card", cardService.getCardById(cardId));
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "card-edit";
    }
    //View form to create a new card
    @GetMapping("/cards/new")
    public String newCardForm(HttpSession session, Model model) {
        model.addAttribute("card", new Card());
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
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
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
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
    public String viewListingDetails(@PathVariable Long id, HttpSession session, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "listing-details";
    }
    //View update listing form
    @GetMapping("/listings/{id}/edit")
    public String showEditListingForm(@PathVariable Long id, HttpSession session,Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        Long providerId = (Long) session.getAttribute("providerId");
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "edit-listing";
    }
    //View create listing form
    @GetMapping("/listings/new/card/{cardId}")
    public String showCreateListingForm(@PathVariable Long cardId, Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        model.addAttribute("card", cardService.getCardById(cardId));
        model.addAttribute("listing", new Listing());
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "create-listing";
    }
}

