package coms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.cartorder.CartItem;

@Repository
public interface cartitemrepo extends JpaRepository<CartItem, Long> {
    CartItem findByProductId(Long productId);
}
