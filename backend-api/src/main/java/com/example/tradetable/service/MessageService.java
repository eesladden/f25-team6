package com.example.tradetable.service;

import com.example.tradetable.entity.Message;
import com.example.tradetable.repository.MessageRepository;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByProviderId(Long providerId) {
        return messageRepository.findByProviderId(providerId);
    }

    public List<Message> getMessagesByCustomerId(Long customerId) {
        return messageRepository.findByCustomerId(customerId);
    }

    public List<Message> getMessagesByProviderIdAndCommentContaining(Long providerId, String text) {
        return messageRepository.searchByProviderIdAndText(providerId, text);
    }

    public List<Message> getMessagesByCustomerIdAndCommentContaining(Long customerId, String text) {
        return messageRepository.searchByCustomerIdAndText(customerId, text);
    }

    public List<Message> getConversationBetweenProviderAndCustomer(Long providerId, Long customerId) {
        return messageRepository.findConversationBetweenProviderAndCustomer(providerId, customerId);
    }

    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new EntityNotFoundException("Message not found with id " + id);
        }
        messageRepository.deleteById(id);
    }
}
