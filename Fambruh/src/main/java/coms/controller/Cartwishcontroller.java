package coms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import coms.model.cartorder.CartItem;
import coms.model.cartorder.wishlist;
import coms.model.product.Product;
import coms.service.Cartwishservice;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Cartwishcontroller {

    @Autowired
    private Cartwishservice cartWishService;

    @GetMapping("/cart")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartWishService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<wishlist>> getAllWishlistItems() {
        List<wishlist> wishlistItems = cartWishService.getAllWishlistItems();
        return ResponseEntity.ok(wishlistItems);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<?> addToCart(@RequestBody Product product, @RequestParam int quantity) {
        cartWishService.addToCart(product, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Method to add a product to the wishlist (only accessible to users)
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/wishlist/add")
    public ResponseEntity<?> addToWishlist(@RequestBody Product product) {
        cartWishService.addToWishlist(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/cart/move/{cartItemId}")
    public ResponseEntity<?> moveCartItemToWishlist(@PathVariable Long cartItemId) {
        cartWishService.moveCartItemToWishlist(cartItemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/wishlist/move/{wishlistItemId}")
    public ResponseEntity<?> moveWishlistItemToCart(@PathVariable Integer wishlistItemId, @RequestParam int quantity) {
        cartWishService.moveWishlistItemToCart(wishlistItemId, quantity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/cart/{cartItemId}")
    public ResponseEntity<?> removeCartItemById(@PathVariable Long cartItemId) {
        cartWishService.removeCartItemById(cartItemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/wishlist/{wishlistItemId}")
    public ResponseEntity<?> removeWishlistItemById(@PathVariable Integer wishlistItemId) {
        cartWishService.removeWishlistItemById(wishlistItemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/cart/update/{cartItemId}")
    public ResponseEntity<?> updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        cartWishService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
