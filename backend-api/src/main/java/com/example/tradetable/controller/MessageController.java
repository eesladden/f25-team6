package com.example.tradetable.controller;

import com.example.tradetable.entity.Message;
import com.example.tradetable.service.MessageService;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ConditionalOnExpression("${my.controller.enabled:false}")
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    /**
     * Send a message.
     * @param message the message to be sent
     * @return the saved message
     */
    @PostMapping
    public ResponseEntity<Message> sendMessage(@Valid @RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
    /**
     * Get messages between a specific provider and customer.
     * @param providerId the ID of the provider
     * @param customerId the ID of the customer
     * @return list of messages between the specified provider and customer
     */
    @GetMapping
    public ResponseEntity<List<Message>> getMessagesByProviderAndCustomer(
            @RequestParam Long providerId,
            @RequestParam Long customerId) {
        List<Message> messages = messageService.getMessagesByProviderAndCustomer(providerId, customerId);
        return ResponseEntity.ok(messages);
    }
}
