package coms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.product.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	public List<Product> findByNameContainingIgnoreCaseOrSaltContainingIgnoreCase(String name, String salt);
	
	public List<Product> findByNameAndIsAvailableTrue(String name);
}