package coms.repository;

import coms.model.cartorder.CartItem;
import coms.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cartitemrepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserUsername(String username);
}
