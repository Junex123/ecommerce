package coms.controller;

import coms.model.extra.newsletter;
import coms.service.newsletterservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/newsletter")
public class Newslettercontroller {

    @Autowired
    private newsletterservice newsletterService;

    // Retrieve all newsletter subscriptions
    @GetMapping("/subscriptions")
    public ResponseEntity<List<newsletter>> getAllSubscriptions() {
        List<newsletter> subscriptions = newsletterService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    // Retrieve a newsletter subscription by email
    @GetMapping("/subscription/{email}")
    public ResponseEntity<?> getSubscriptionByEmail(@PathVariable("email") String email) {
        Optional<newsletter> subscription = newsletterService.getSubscriptionByEmail(email);
        return subscription.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new newsletter subscription
    @PostMapping("/subscribe")
    public ResponseEntity<newsletter> subscribe(@RequestBody newsletter subscription) {
        newsletter newSubscription = newsletterService.subscribe(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSubscription);
    }

    // Remove a newsletter subscription by email
    @DeleteMapping("/unsubscribe/{email}")
    public ResponseEntity<?> unsubscribe(@PathVariable("email") String email) {
        newsletterService.unsubscribe(email);
        return ResponseEntity.ok().build();
    }
}
