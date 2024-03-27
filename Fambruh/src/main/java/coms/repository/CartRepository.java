package coms.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import coms.model.cartorder.Cart;
import coms.model.user.*;
public interface CartRepository extends CrudRepository<Cart, Long> {
	public List<Cart> findByUser(User user);
}