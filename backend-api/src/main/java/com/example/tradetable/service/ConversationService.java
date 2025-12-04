package com.example.tradetable.service;

import com.example.tradetable.entity.Conversation;
import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.Listing;
import com.example.tradetable.entity.Provider;
import com.example.tradetable.repository.ConversationRepository;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final CustomerService customerService;   // used in getOrCreateConversation
    private final ListingService listingService;     // used in getOrCreateConversation

    /**
     * Creates a new conversation.
     * @param conversation the conversation to be created
     * @return the created conversation
     */
    public Conversation createConversation(Conversation conversation) {
        if (conversation == null) {
            throw new IllegalArgumentException("Conversation must not be null");
        } else if (conversation.getProvider() == null || conversation.getCustomer() == null || conversation.getListing() == null) {
            throw new IllegalArgumentException("Provider, Customer, and Listing must not be null");
        }
        return conversationRepository.save(conversation);
    }
    /**
     * Deletes a conversation by its ID.
     * @param id the ID of the conversation to be deleted
     */
    public void deleteConversation(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Conversation ID must not be null");
        } else if (!conversationRepository.existsById(id)) {
            throw new EntityNotFoundException("Conversation not found with id " + id);
        }
        conversationRepository.deleteById(id);
    }
    /**
     * Get conversations by providerId
     * @param providerId the ID of the provider
     * @return
     */
    public List<Conversation> getConversationsByProviderId(Long providerId) {
        return conversationRepository.findByProviderId(providerId);
    }
    /**
     * Get conversations by customerId
     * @param customerId the ID of the customer
     * @return
     */
    public List<Conversation> getConversationsByCustomerId(Long customerId) {
        return conversationRepository.findByCustomerId(customerId);
    }

    // NEW: get or create a conversation for this listing + customer
    public Conversation getOrCreateConversation(Long listingId, Long customerId) {
        if (listingId == null || customerId == null) {
            throw new IllegalArgumentException("Listing ID and Customer ID must not be null");
        }

        Listing listing = listingService.getListingById(listingId);
        Provider provider = listing.getProvider();
        Customer customer = customerService.get(customerId);

        // Look for an existing conversation for this (listing, customer, provider)
        List<Conversation> existing =
                conversationRepository.findByListingIdAndCustomerIdAndProviderId(
                        listingId, customerId, provider.getId());

        if (!existing.isEmpty()) {
            return existing.get(0);
        }

        // Otherwise create a new one
        Conversation convo = new Conversation();
        convo.setListing(listing);
        convo.setCustomer(customer);
        convo.setProvider(provider);
        convo.setLastUpdated(java.time.LocalDateTime.now());
        // lastUpdatedString can be set by entity or here if needed

        return conversationRepository.save(convo);
    }

    /**
     * Get conversations by listingId
     * @param listingId the ID of the listing
     * @return
     */
    public List<Conversation> getConversationsByListingId(Long listingId) {
        return conversationRepository.findByListingId(listingId);
    }
    /**
     * Retrieves a conversation by its ID.
     * @param conversationId the ID of the conversation
     * @return the conversation with the specified ID
     */
    public Conversation getConversationById(Long conversationId) {
        if (conversationId == null) {
            throw new IllegalArgumentException("Conversation ID must not be null");
        }
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new EntityNotFoundException("Conversation not found with id " + conversationId));
    }
    /**
     * Updates the lastUpdated field of a conversation.
     * @param conversationId the ID of the conversation to be updated
     */
    public void updateLastUpdated(Long conversationId) {
        Conversation conversation = getConversationById(conversationId);
        conversation.setLastUpdated(java.time.LocalDateTime.now());
        conversation.setLastUpdatedString(conversation.getLastUpdated().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        conversationRepository.save(conversation);
    }
}
