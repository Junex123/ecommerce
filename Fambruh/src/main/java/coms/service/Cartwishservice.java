package coms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.configuration.JwtUtil;
import coms.model.cartorder.Cart;
import coms.model.product.Product;
import coms.model.user.User;
import coms.repository.CartRepository;
import coms.repository.ProductRepo;
import coms.repository.UserRepo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Cartwishservice {

    @Autowired
    private ProductRepo productrepo;

    @Autowired
    private UserRepo userrepo;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void deleteCartItem(Long cartId, String jwtToken) {
        String username = extractUsernameFromToken(jwtToken);
        if (username != null) {
            User user = userrepo.findByUsername(username);
            if (user != null) {
                cartRepository.deleteById(cartId);
            } else {
                throw new RuntimeException("User not found");
            }
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    public Cart addToCart(Long pid, String jwtToken) {
        String username = extractUsernameFromToken(jwtToken);
        if (username != null) {
            User user = userrepo.findByUsername(username);
            if (user != null) {
                Product product = productrepo.findById(pid).orElse(null);
                if (product != null) {
                    List<Cart> cartList = cartRepository.findByUser(user);
                    List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getPid() == pid).collect(Collectors.toList());

                    if (filteredList.isEmpty()) {
                        Cart cart = new Cart(product, user);
                        return cartRepository.save(cart);
                    } else {
                        return null; // Product already exists in the cart
                    }
                } else {
                    throw new RuntimeException("Product not found");
                }
            } else {
                throw new RuntimeException("User not found");
            }
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    public List<Cart> getCartDetails(String jwtToken) {
        String username = extractUsernameFromToken(jwtToken);
        if (username != null) {
            User user = userrepo.findByUsername(username);
            if (user != null) {
                return cartRepository.findByUser(user);
            } else {
                throw new RuntimeException("User not found");
            }
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    // Method to extract username from JWT token using JwtUtil
    private String extractUsernameFromToken(String jwtToken) {
        try {
            return jwtUtil.extractUsername(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
