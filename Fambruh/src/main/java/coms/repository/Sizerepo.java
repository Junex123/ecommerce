package coms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.cartorder.Wishlist;
import coms.model.product.ProductSize;

	@Repository
	public interface Sizerepo extends JpaRepository<ProductSize, Integer> {
	   
	}
