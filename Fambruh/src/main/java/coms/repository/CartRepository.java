package coms.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import coms.model.cartorder.CartItem;
import coms.model.user.*;
public interface CartRepository extends CrudRepository<CartItem, Long> {
	public List<CartItem> findByUser(User user);
}