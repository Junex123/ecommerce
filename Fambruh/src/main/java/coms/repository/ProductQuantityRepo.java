package coms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.product.ProductQuantity;


@Repository
public interface ProductQuantityRepo extends JpaRepository<ProductQuantity, Long>{

}
