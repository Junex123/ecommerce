package coms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import coms.model.product.ProductImageDetail;

@Repository
public interface detailimagerepo extends JpaRepository<ProductImageDetail, Long> {
   
}
