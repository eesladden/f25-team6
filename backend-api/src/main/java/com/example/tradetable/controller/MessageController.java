package com.example.tradetable.controller;

import com.example.tradetable.entity.Message;
import com.example.tradetable.service.MessageService;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    
    @PostMapping
    public ResponseEntity<Message> sendMessage(@Valid @RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Message>> getMessagesByProviderId(@PathVariable Long providerId) {
        List<Message> messages = messageService.getMessagesByProviderId(providerId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Message>> getMessagesByCustomerId(@PathVariable Long customerId) {
        List<Message> messages = messageService.getMessagesByCustomerId(customerId);
        return ResponseEntity.ok(messages);
    }
}
