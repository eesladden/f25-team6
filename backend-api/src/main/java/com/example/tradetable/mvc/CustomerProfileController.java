package com.example.tradetable.mvc;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/profile")
public class CustomerProfileController {

    private final CustomerService customerService;

    // Utility: get logged-in customer
    private Customer getCurrentCustomer(HttpSession session) {
        Long id = (Long) session.getAttribute("customerId");
        if (id == null) {
            throw new IllegalStateException("No logged-in customer.");
        }
        return customerService.get(id);
    }

    // ---------------------------
    // PROFILE PAGE
    // ---------------------------
    @GetMapping
    public String viewProfile(Model model, HttpSession session) {
        Customer me = getCurrentCustomer(session);
        model.addAttribute("me", me);
        return "customer/customer-profile";
    }

    // ---------------------------
    // EDIT PROFILE FORM
    // ---------------------------
    @GetMapping("/edit")
    public String editProfile(Model model, HttpSession session) {
        model.addAttribute("me", getCurrentCustomer(session));
        return "customer/customer-edit-profile";
    }

    @PostMapping("/edit")
    public String saveProfile(
            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String bio,
            @RequestParam(required = false) String preferredSets,
            @RequestParam(required = false) String shippingRegion,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String profileImageUrl,
            HttpSession session,
            Model model
    ) {
        Customer me = getCurrentCustomer(session);

        Customer patch = new Customer();
        patch.setDisplayName(displayName);
        patch.setBio(bio);
        patch.setPreferredSets(preferredSets);
        patch.setShippingRegion(shippingRegion);
        patch.setEmail(email);
        patch.setPhoneNumber(phoneNumber);  
        patch.setProfileImageUrl(profileImageUrl);

        customerService.updateProfile(me.getId(), patch);

        try {
            customerService.updateProfile(me.getId(), patch);
            return "redirect:/customer/profile";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("profileError", ex.getMessage());
            model.addAttribute("me", me);
            return "customer/customer-edit-profile";
        }
    }

    // ---------------------------
    // CHANGE PASSWORD
    // ---------------------------
    @GetMapping("/change-password")
    public String showChangePasswordPage() {
        return "customer/customer-change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            HttpSession session,
            Model model
    ) {
        Customer me = getCurrentCustomer(session);

        if (!customerService.checkLogin(me.getEmail(), currentPassword) &&
            !customerService.checkLogin(me.getUsername(), currentPassword)) {

            model.addAttribute("changePasswordError", true);
            return "customer/customer-change-password";
        }

        me.setPasswordHash(null); // reset old
        me.setPassword(newPassword);
        customerService.signup(me); // re-hash & save

        return "redirect:/customer/profile";
    }

    // ---------------------------
    // DELETE ACCOUNT
    // ---------------------------
    @PostMapping("/delete")
    public String deleteAccount(HttpSession session) {
        Customer me = getCurrentCustomer(session);
        customerService.delete(me.getId());
        session.invalidate();
        return "redirect:/";
    }
}
