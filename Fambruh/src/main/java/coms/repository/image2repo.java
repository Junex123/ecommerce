package coms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import coms.model.product.ProductImage2;

@Repository
public interface image2repo extends JpaRepository<ProductImage2, Long> {
 
}
