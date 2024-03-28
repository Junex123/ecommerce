package coms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.product.ProductImage3;

@Repository
public interface image3repo extends JpaRepository<ProductImage3, Long> {
 
}
