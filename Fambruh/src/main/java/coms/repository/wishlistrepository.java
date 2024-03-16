package coms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.cartorder.Wishlist;
import coms.model.user.User;

import java.util.List;

@Repository
public interface wishlistrepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByUserUsername(String username);
}
