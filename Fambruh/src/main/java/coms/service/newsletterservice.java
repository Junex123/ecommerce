package coms.service;

import coms.model.extra.newsletter;
import coms.repository.newsletterrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class newsletterservice {

    @Autowired
    private newsletterrepo newsletterRepository;

    /**
     * Retrieves all newsletter subscriptions.
     *
     * @return List of Newsletter objects representing all subscriptions
     */
    public List<newsletter> getAllSubscriptions() {
        return newsletterRepository.findAll();
    }

    /**
     * Retrieves a newsletter subscription by email.
     *
     * @param email The email address of the subscriber
     * @return Optional containing the Newsletter object if found, otherwise empty
     */
    public Optional<newsletter> getSubscriptionByEmail(String email) {
        return newsletterRepository.findByEmail(email);
    }

    /**
     * Adds a new newsletter subscription.
     *
     * @param subscription The Newsletter object representing the new subscription
     * @return The saved Newsletter object
     */
    public newsletter subscribe(newsletter subscription) {
        return newsletterRepository.save(subscription);
    }

    /**
     * Removes a newsletter subscription by email.
     *
     * @param email The email address of the subscriber to unsubscribe
     */
    public void unsubscribe(String email) {
        newsletterRepository.deleteByEmail(email);
    }

    // Add method to send emails to subscribers
    // Add any other admin-related methods as needed
}
