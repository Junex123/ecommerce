package coms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import coms.model.cartorder.CartItem;
import coms.model.cartorder.Wishlist;
import coms.model.dtos.CartItemResponseDto;
import coms.model.product.Product;
import coms.repository.Size;
import coms.service.Cartwishservice;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Cartwishcontroller {

    @Autowired
    private Cartwishservice cartWishService;

    @GetMapping("/cart")
    public ResponseEntity<List<CartItemResponseDto>> getAllCartItems(@RequestParam String username) {
    	List<CartItemResponseDto> cartItems = cartWishService.getAllCartItemsByUsername(username);
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<Wishlist>> getAllWishlistItems(@RequestParam String username) {
        List<Wishlist> wishlistItems = cartWishService.getAllWishlistItemsByUsername(username);
        return ResponseEntity.ok(wishlistItems);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<?> addToCart(@RequestBody Product product, @RequestParam int quantity, @RequestParam String username) {
        cartWishService.addToCart(product, quantity, username);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/wishlist/add")
    public ResponseEntity<?> addToWishlist(@RequestBody Product product, @RequestParam String username) {
        cartWishService.addToWishlist(product, username);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/cart/move/{cartItemId}")
    public ResponseEntity<?> moveCartItemToWishlist(@PathVariable Long cartItemId, @RequestParam String username) {
        cartWishService.moveCartItemToWishlist(cartItemId, username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/wishlist/move/{wishlistItemId}")
    public ResponseEntity<?> moveWishlistItemToCart(@PathVariable int wishlistItemId, @RequestParam String username) {
        cartWishService.moveWishlistItemToCart(wishlistItemId, username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/cart/{cartItemId}")
    public ResponseEntity<?> removeCartItemById(@PathVariable Long cartItemId) {
        cartWishService.removeCartItemById(cartItemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/wishlist/{wishlistItemId}")
    public ResponseEntity<?> removeWishlistItemById(@PathVariable int wishlistItemId) {
        cartWishService.removeWishlistItemById(wishlistItemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/cart/update/{cartItemId}")
    public ResponseEntity<?> updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        cartWishService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Additional method to update the size of a cart item
    @PutMapping("/cart/update/size/{cartItemId}")
    public ResponseEntity<?> updateCartItemSize(@PathVariable Long cartItemId, @RequestParam Size size) {
        cartWishService.updateCartItemSize(cartItemId, size);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
