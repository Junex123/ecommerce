package coms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coms.model.cartorder.CartItem;

@Repository
public interface Itemrepo extends JpaRepository<CartItem, Long> {
    // Add custom query methods if needed
}
