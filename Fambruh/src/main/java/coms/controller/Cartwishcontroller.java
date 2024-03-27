package coms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import coms.model.cartorder.Cart;

import coms.model.cartorder.Wishlist;
import coms.model.product.Product;
import coms.repository.Size;
import coms.service.Cartwishservice;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Cartwishcontroller {

    @Autowired
    private Cartwishservice cartWishService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/addToCart/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable(name = "productId") Long productId, @RequestHeader("Authorization") String jwtToken) {
        Cart cart = cartWishService.addToCart(productId, jwtToken);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping("/deleteCartItem/{cartId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable(name = "cartId") Long cartId, @RequestHeader("Authorization") String jwtToken) {
        cartWishService.deleteCartItem(cartId, jwtToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/getCartDetails")
    public ResponseEntity<List<Cart>> getCartDetails(@RequestHeader("Authorization") String jwtToken) {
        List<Cart> cartDetails = cartWishService.getCartDetails(jwtToken);
        return new ResponseEntity<>(cartDetails, HttpStatus.OK);
    }
}
