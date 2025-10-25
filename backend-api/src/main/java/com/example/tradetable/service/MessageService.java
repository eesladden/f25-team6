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
        return messageRepository.findAll().stream()
                .filter(m -> m.getProvider().getId().equals(providerId))
                .toList();
    }

    public List<Message> getMessagesByCustomerId(Long customerId) {
        return messageRepository.findAll().stream()
                .filter(m -> m.getCustomer().getId().equals(customerId))
                .toList();
    }

    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new EntityNotFoundException("Message not found with id " + id);
        }
        messageRepository.deleteById(id);
    }
}
