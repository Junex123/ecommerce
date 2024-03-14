package coms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.model.cartorder.CartItem;
import coms.model.cartorder.UserOrder;
import coms.model.product.Product;
import coms.model.product.ProductQuantity;
import coms.model.product.comboproduct;
import coms.repository.OrderRepo;
import coms.repository.ProductQuantityRepo;

@Service
public class UserOrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductQuantityRepo productQuantityRepo;
    
    @Autowired
    private EmailService emailService;

    /**
     * Saves a new user order.
     * 
     * @param userOrder         The UserOrder object representing the order to be saved
     * @param userRecipientEmail   The email address of the user to send confirmation email
     * @param adminRecipientEmail  The email address of the admin to send confirmation email
     * @param emailContent      The content of the email to be sent
     * @return The saved UserOrder object
     */
    public UserOrder saveOrder(UserOrder userOrder, String userRecipientEmail, String adminRecipientEmail, String emailContent) {
        // Save the order
        UserOrder savedOrder = orderRepo.save(userOrder);

        // Send order confirmation emails to user and admin
        emailService.sendOrderConfirmationEmail(userRecipientEmail, emailContent);
        emailService.sendOrderConfirmationEmail(adminRecipientEmail, emailContent);

        return savedOrder;
    }

    /**
     * Retrieves all orders for a given user.
     * 
     * @param username The username of the user whose orders are to be retrieved
     * @return A list of UserOrder objects representing the user's orders
     */
    public List<UserOrder> getUserOrders(String username) {
        return orderRepo.findByUsername(username);
    }

    /**
     * Retrieves an order by its ID.
     * 
     * @param oid The ID of the order to retrieve
     * @return The UserOrder object if found, otherwise null
     */
    public UserOrder getOrderById(Long oid) {
        return orderRepo.findById(oid).orElse(null);
    }

    /**
     * Retrieves all orders.
     * 
     * @return A list of all UserOrder objects
     */
    public List<UserOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    /**
     * Deletes an order by its ID.
     * 
     * @param oid The ID of the order to delete
     */
    public void deleteOrder(Long oid) {
        orderRepo.deleteById(oid);
    }

    /**
     * Updates the status of an order.
     * 
     * @param oid    The ID of the order to update
     * @param status The new status to set for the order
     */
    public void updateOrderStatus(Long oid, String status) {
        UserOrder order = orderRepo.findById(oid).orElse(null);
        if (order != null) {
            order.setStatus(status);
            orderRepo.save(order);
        }
    }

    /**
     * Saves a new product quantity.
     * 
     * @param productQuantity The ProductQuantity object representing the product quantity to be saved
     */
    public void saveProductQuantity(ProductQuantity productQuantity) {
        productQuantityRepo.save(productQuantity);
    }

    /**
     * Retrieves all orders for a given user by username.
     * 
     * @param username The username of the user whose orders are to be retrieved
     * @return A list of UserOrder objects representing the user's orders
     */
    public List<UserOrder> getOrdersByUsername(String username) {
        return orderRepo.findByUsername(username);
    }
    
    // New method to update delivery status
    public void updateDeliveryStatus(Long oid, String status) {
        UserOrder order = orderRepo.findById(oid).orElse(null);
        if (order != null) {
            order.setStatus(status);
            orderRepo.save(order);
        }
    }

    // New method to update return status
    public void updateReturnStatus(Long oid, String status) {
        UserOrder order = orderRepo.findById(oid).orElse(null);
        if (order != null) {
            order.setReturnStatus(status);
            orderRepo.save(order);
        }
    }

    // New method to update exchange status
    public void updateExchangeStatus(Long oid, String status) {
        UserOrder order = orderRepo.findById(oid).orElse(null);
        if (order != null) {
            order.setReplacementStatus(status);
            orderRepo.save(order);
        }
    }

    /**
     * Saves a new user order with product quantities.
     * 
     * @param userOrder           The UserOrder object representing the order to be saved
     * @param userRecipientEmail  The email address of the user to send confirmation email
     * @param adminRecipientEmail The email address of the admin to send confirmation email
     * @param emailContent        The content of the email to be sent
     * @return The saved UserOrder object
     */
    public UserOrder saveOrderWithProductQuantities(UserOrder userOrder, List<CartItem> cartItems, String userRecipientEmail, String adminRecipientEmail, String emailContent) {
        UserOrder savedOrder = orderRepo.save(userOrder);

        // Save product quantities
        Set<ProductQuantity> productQuantities = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct() != null) {
                // For regular products
                ProductQuantity productQuantity = new ProductQuantity();
                productQuantity.setProduct(cartItem.getProduct());
                productQuantity.setQuantity(cartItem.getQuantity());
                productQuantities.add(productQuantity);
                productQuantityRepo.save(productQuantity);
            } else if (cartItem.getComboproduct() != null) {
                // For combo products
                comboproduct comboProduct = cartItem.getComboproduct();
                Set<Product> products = new HashSet<>();
                products.add(comboProduct.getProduct1());
                products.add(comboProduct.getProduct2());
                for (Product product : products) {
                    ProductQuantity productQuantity = new ProductQuantity();
                    productQuantity.setProduct(product);
                    productQuantity.setQuantity(cartItem.getQuantity());
                    productQuantities.add(productQuantity);
                    productQuantityRepo.save(productQuantity);
                }
            }
        }

        savedOrder.setProducts(productQuantities); // Set product quantities to the order

        // Send order confirmation emails
        emailService.sendOrderConfirmationEmail(userRecipientEmail, emailContent);
        emailService.sendOrderConfirmationEmail(adminRecipientEmail, emailContent);

        return savedOrder;
    }




}
