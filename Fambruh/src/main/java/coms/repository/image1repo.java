package coms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import coms.model.product.ProductImage1;
import coms.model.product.ProductImageMain;

@Repository
public  interface image1repo extends JpaRepository<ProductImage1, Long> {


 
}
