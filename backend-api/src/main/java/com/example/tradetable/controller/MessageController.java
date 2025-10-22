package com.example.tradetable.controller;

import com.example.tradetable.entity.Message;
import com.example.tradetable.service.MessageService;

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
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Message message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<java.util.List<Message>> getMessagesByProviderId(@PathVariable Long providerId) {
        java.util.List<Message> messages = messageService.getMessagesByProviderId(providerId);
        return ResponseEntity.ok(messages);
    }
    
    @GetMapping("/provider/{providerId}/search")
    public ResponseEntity<java.util.List<Message>> getMessagesByProviderIdAndCommentContaining(
            @PathVariable Long providerId, @RequestParam String text) {
        java.util.List<Message> messages = messageService.getMessagesByProviderIdAndCommentContaining(providerId, text);
        return ResponseEntity.ok(messages);
    }
}
