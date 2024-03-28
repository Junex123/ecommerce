package coms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import coms.model.product.ProductImageMain;

@Repository
public interface mainimagerepo extends JpaRepository<ProductImageMain, Long> {
 
}
