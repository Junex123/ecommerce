package coms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.cartorder.wishlist;

@Repository
public interface wishlistrepository extends JpaRepository<wishlist, Integer> {
    // You can add custom query methods if needed
}
