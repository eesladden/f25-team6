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
@RequestMapping("/customers")
public class CustomerAuthController {

    private final CustomerService customerService;

    // ---------------------------
    // LOGIN PAGE
    // ---------------------------
    @GetMapping("/login")
    public String showLoginPage() {
        return "customer/customer-login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String usernameOrEmail,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        boolean ok = false;

        try {
            ok = customerService.checkLogin(usernameOrEmail, password);
        } catch (Exception ignored) {}

        if (!ok) {
            model.addAttribute("loginError", true);
            return "customer/customer-login";
        }

        // Retrieve the customer
        Customer c = customerService.findByUsernameOrEmail(usernameOrEmail);

        // Store ID in session
        session.setAttribute("customerId", c.getId());

        return "redirect:/customer/dashboard";
    }

    // ---------------------------
    // SIGN UP PAGE
    // ---------------------------
    @GetMapping("/signup")
    public String showSignupPage() {
        return "customer/customer-signup";
    }

    @PostMapping("/signup")
    public String processSignup(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String profileImageUrl,
            HttpSession session,
            Model model
    ) {
        // password mismatch
        if (!password.equals(confirmPassword)) {
            model.addAttribute("passwordMismatchError", true);
            return "customer/customer-signup";
        }

        try {
            Customer incoming = new Customer();
            incoming.setEmail(email);
            incoming.setUsername(username);
            incoming.setPhoneNumber(phoneNumber);
            incoming.setPassword(password);
            incoming.setDisplayName(displayName);
            incoming.setProfileImageUrl(profileImageUrl); 

            Customer saved = customerService.signup(incoming);

            // auto-login
            session.setAttribute("customerId", saved.getId());
            return "redirect:/customer/dashboard";

        } catch (IllegalArgumentException ex) {
            model.addAttribute("signupError", ex.getMessage());
            return "customer/customer-signup";
        }
    }

    // ---------------------------
    // LOGOUT
    // ---------------------------
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
