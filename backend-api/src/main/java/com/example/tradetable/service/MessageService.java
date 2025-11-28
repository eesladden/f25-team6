package com.example.tradetable.service;

import com.example.tradetable.entity.Message;
import com.example.tradetable.repository.MessageRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    
    /**
     * Sends a message by saving it to the repository.
     * @param message the message to be sent
     * @return the saved message
     */
    public Message sendMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message must not be null");
        }
        return messageRepository.save(message);
    }
    /**
     * Retrieves messages between a specific provider and customer.
     * @param providerId the ID of the provider
     * @param customerId the ID of the customer
     * @return list of messages between the specified provider and customer
     */
    public List<Message> getMessagesByProviderAndCustomer(Long providerId, Long customerId) {
        if (providerId == null || customerId == null) {
            throw new IllegalArgumentException("Provider ID and Customer ID must not be null");
        }
        return messageRepository.findByProviderIdAndCustomerId(providerId, customerId);
    }
    /**
     * Retrieves messages associated with a specific conversation.
     * @param conversationId the ID of the conversation
     * @return list of messages in the specified conversation
     */
    public List<Message> getMessagesByConversationId(Long conversationId) {
        if (conversationId == null) {
            throw new IllegalArgumentException("Conversation ID must not be null");
        }
        return messageRepository.findAll().stream()
                .filter(message -> message.getConversation() != null && message.getConversation().getId().equals(conversationId))
                .toList();
    }
    /**
     * Deletes a message by its ID.
     * @param messageId the ID of the message to be deleted
     */
    public void deleteMessageById(Long messageId) {
        if (messageId == null) {
            throw new IllegalArgumentException("Message ID must not be null");
        }
        messageRepository.deleteById(messageId);
    }
}
