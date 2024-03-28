package coms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import coms.model.cartorder.CartItem;
import coms.model.user.User;
public interface CartRepository extends JpaRepository<CartItem, Long> {
	public List<CartItem> findByUser(User user);

	public List<CartItem> findByUserUsername(String username);

import coms.model.cartorder.CartItem;
import coms.model.user.*;
public interface CartRepository extends CrudRepository<CartItem, Long> {
	public List<CartItem> findByUser(User user);

}