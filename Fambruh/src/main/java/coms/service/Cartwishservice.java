package coms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.model.cartorder.CartItem;
import coms.model.cartorder.wishlist;
import coms.model.product.Product;
import coms.model.product.comboproduct;
import coms.repository.cartitemrepo;
import coms.repository.wishlistrepository;


@Service
public class Cartwishservice {

    @Autowired
    private cartitemrepo cartItemRepo;

    @Autowired
    private wishlistrepository wishlistRepo;

    // Method to retrieve all cart items
    public List<CartItem> getAllCartItems() {
        return cartItemRepo.findAll();
    }

    // Method to retrieve all wishlist items
    public List<wishlist> getAllWishlistItems() {
        return wishlistRepo.findAll();
    }

    // Method to remove a cart item by its ID
    public void removeCartItemById(Long cartItemId) {
        cartItemRepo.deleteById(cartItemId);
    }

    // Method to remove a wishlist item by its ID
    public void removeWishlistItemById(Integer wishid) {
        wishlistRepo.deleteById(wishid);
    }

    // Method to add a product to the wishlist
    public void addToWishlist(Product product) {
        wishlist wishlistItem = new wishlist(product);
        wishlistRepo.save(wishlistItem);
    }

    // Method to add a product to the cart with a specified quantity
    public void addToCart(Product product, int quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        cartItemRepo.save(cartItem);
    }

    // Method to move a cart item to the wishlist
    public void moveCartItemToWishlist(Long cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            Product product = cartItem.getProduct();
            removeCartItemById(cartItemId);
            addToWishlist(product);
        }
    }

    // Method to move a wishlist item to the cart with a specified quantity
    public void moveWishlistItemToCart(Integer wishlistItemId, int quantity) {
        wishlist wishlistItem = wishlistRepo.findById(wishlistItemId).orElse(null);
        if (wishlistItem != null) {
            Product product = wishlistItem.getProduct();
            removeWishlistItemById(wishlistItemId);
            addToCart(product, quantity);
        }
    }

    // Method to update the quantity of a cart item by product ID
    public void updateCartItemQuantity(Long productId, int quantity) {
        CartItem cartItem = cartItemRepo.findByProductId(productId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartItemRepo.save(cartItem);
        }
    }

    // Method to add a combo product to the cart
    public void addComboProductToCart(comboproduct comboProduct) {
        CartItem cartItem = new CartItem(comboProduct, 1); // Default quantity for combo products
        cartItemRepo.save(cartItem);
    }

    // Method to remove a combo product from the cart
    public void removeComboProductFromCart(Long cartItemId) {
        cartItemRepo.deleteById(cartItemId);
    }

    // Other methods...
}
