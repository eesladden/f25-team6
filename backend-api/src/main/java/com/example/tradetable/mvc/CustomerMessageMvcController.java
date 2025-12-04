package com.example.tradetable.mvc;

import com.example.tradetable.entity.Conversation;
import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.Listing;
import com.example.tradetable.entity.Message;
import com.example.tradetable.entity.Provider;
import com.example.tradetable.entity.Recipient;
import com.example.tradetable.entity.Sender;
import com.example.tradetable.service.ConversationService;
import com.example.tradetable.service.CustomerService;
import com.example.tradetable.service.ListingService;
import com.example.tradetable.service.MessageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/messages")
public class CustomerMessageMvcController {

    private final ConversationService conversationService;
    private final MessageService messageService;
    private final CustomerService customerService;
    private final ListingService listingService;

    private Long currentCustomerId(HttpSession session) {
        Object id = session.getAttribute("customerId");
        if (id == null) {
            throw new IllegalStateException("No current customer in session. Please log in first.");
        }
        return (Long) id;
    }

    // List all conversations for this customer
    @GetMapping("/conversations/my-conversations")
    public String myConversations(HttpSession session, Model model) {
        Long customerId = currentCustomerId(session);
        Customer customer = customerService.get(customerId);

        List<Conversation> conversations =
                conversationService.getConversationsByCustomerId(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("conversations", conversations);
        return "customer/customer-my-conversations";
    }

    // View messages in a single conversation
    @GetMapping("/conversations/my-conversations/{conversationId}")
    public String viewConversation(@PathVariable Long conversationId,
                                   HttpSession session,
                                   Model model,
                                   RedirectAttributes ra) {
        Long customerId = currentCustomerId(session);
        Conversation convo = conversationService.getConversationById(conversationId);

        if (!convo.getCustomer().getId().equals(customerId)) {
            ra.addFlashAttribute("msg", "You are not part of that conversation.");
            return "redirect:/customer/messages/conversations/my-conversations";
        }

        model.addAttribute("customer", customerService.get(customerId));
        model.addAttribute("conversation", convo);
        model.addAttribute("messages", messageService.getMessagesByConversationId(conversationId));
        return "customer/customer-conversation-messages";
    }

    // Send a message as CUSTOMER in an existing conversation
    @PostMapping("/conversations/{conversationId}/send")
    public String sendFromCustomer(@PathVariable Long conversationId,
                                   @RequestParam String content,
                                   HttpSession session,
                                   RedirectAttributes ra) {
        Long customerId = currentCustomerId(session);
        Conversation convo = conversationService.getConversationById(conversationId);

        if (!convo.getCustomer().getId().equals(customerId)) {
            ra.addFlashAttribute("msg", "You are not part of that conversation.");
            return "redirect:/customer/messages/conversations/my-conversations";
        }

        Message message = new Message();
        message.setContent(content);
        message.setSender(Sender.CUSTOMER);
        message.setRecipient(Recipient.PROVIDER);
        message.setCustomer(customerService.get(customerId));
        message.setProvider(convo.getProvider());
        message.setConversation(convo);

        messageService.sendMessage(message);
        conversationService.updateLastUpdated(conversationId);

        return "redirect:/customer/messages/conversations/my-conversations/" + conversationId;
    }

    // Start a new conversation from a listing (or reuse existing one)
    @PostMapping("/conversations/start")
    public String startConversation(@RequestParam Long listingId,
                                    @RequestParam String content,
                                    HttpSession session,
                                    RedirectAttributes ra) {
        Long customerId = currentCustomerId(session);
        Listing listing = listingService.getListingById(listingId);
        Provider provider = listing.getProvider();

        Conversation convo = conversationService.getOrCreateConversation(listingId, customerId);

        Message message = new Message();
        message.setContent(content);
        message.setSender(Sender.CUSTOMER);
        message.setRecipient(Recipient.PROVIDER);
        message.setCustomer(customerService.get(customerId));
        message.setProvider(provider);
        message.setConversation(convo);

        messageService.sendMessage(message);
        conversationService.updateLastUpdated(convo.getId());

        ra.addFlashAttribute("msg", "Message sent to provider.");
        return "redirect:/customer/messages/conversations/my-conversations/" + convo.getId();
    }
}
