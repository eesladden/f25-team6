package com.example.tradetable.controller;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;
    public CustomerController(CustomerService service) { this.service = service; }

    // ----- SIGN UP -----
    // Send a Customer JSON including a "password" field.
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer signup(@Valid @RequestBody Customer body) {
        return service.signup(body);
    }

    // ----- LOGIN -----
    public record LoginBody(String usernameOrEmail, String password) {}
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginBody body) {
        boolean ok = service.checkLogin(body.usernameOrEmail(), body.password());
        if (!ok) throw new IllegalArgumentException("Invalid credentials");
        var c = service.findByUsernameOrEmail(body.usernameOrEmail());
        return Map.of("customerId", c.getId(), "username", c.getUsername(), "message", "Login successful");
    }

    // ----- PROFILE -----
    @GetMapping("/{id}")
    public Customer byId(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer patch) {
        return service.updateProfile(id, patch);
    }

    @GetMapping
    public List<Customer> list() { return service.listAll(); }

    // ----- Error mapping (simple) -----
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String badRequest(IllegalArgumentException ex) { return ex.getMessage(); }
}
