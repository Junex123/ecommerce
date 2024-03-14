package coms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms.model.product.comboproduct;

@Repository
public interface ComboProductRepository extends JpaRepository<comboproduct, Long> {
    // Add custom query methods if needed
}
