package coms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import coms.model.cartorder.CartItem;
import coms.model.cartorder.Wishlist;
import coms.model.product.Product;
import coms.repository.*;
import coms.model.user.User;
import coms.repository.Size;

import java.util.List;

@Service
public class Cartwishservice {

    @Autowired
    private cartitemrepo cartItemRepository;

    @Autowired
    private wishlistrepository wishlistRepository;

    @Autowired
    private UserRepo userRepository;

    // Method to retrieve all cart items by user's username
    public List<CartItem> getAllCartItemsByUsername(String username) {
        return cartItemRepository.findByUserUsername(username);
    }

    // Method to retrieve all wishlist items by user's username
    public List<Wishlist> getAllWishlistItemsByUsername(String username) {
        return wishlistRepository.findByUserUsername(username);
    }

    // Method to remove a cart item by its ID
    public void removeCartItemById(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // Method to remove a wishlist item by its ID
    public void removeWishlistItemById(int wishlistItemId) {
        wishlistRepository.deleteById(wishlistItemId);
    }

    // Method to add a product to the wishlist
    public void addToWishlist(Product product, String username) {
        User user = userRepository.findByUsername(username);
        Wishlist wishlistItem = new Wishlist(user, product);
        wishlistRepository.save(wishlistItem);
    }

    // Method to add a product to the cart
    public void addToCart(Product product, int quantity, String username) {
        User user = userRepository.findByUsername(username);
        CartItem cartItem = new CartItem(user, product, null, null); // Assuming no combo product and size
        cartItemRepository.save(cartItem);
    }

    // Method to move a cart item to the wishlist
    public void moveCartItemToWishlist(Long cartItemId, String username) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            Product product = cartItem.getProduct();
            removeCartItemById(cartItemId);
            addToWishlist(product, username);
        }
    }

    // Method to move a wishlist item to the cart
    public void moveWishlistItemToCart(int wishlistItemId, String username) {
        Wishlist wishlistItem = wishlistRepository.findById(wishlistItemId).orElse(null);
        if (wishlistItem != null) {
            Product product = wishlistItem.getProduct();
            removeWishlistItemById(wishlistItemId);
            addToCart(product, 1, username);
        }
    }

    // Method to update the quantity of a cart item by product ID
    public void updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

    // Method to update the size of a cart item
    public void updateCartItemSize(Long cartItemId, Size size) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            cartItem.setSize(size);
            cartItemRepository.save(cartItem);
        }
    }

    // Other methods...
}
