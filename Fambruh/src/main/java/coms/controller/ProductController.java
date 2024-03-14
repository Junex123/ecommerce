package coms.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coms.configuration.ImageUtil;
import coms.model.*;
import coms.model.product.Product;
import coms.model.product.ProductImage;
import coms.model.product.comboproduct;
import coms.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    // Add new product
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add/product")
    public ResponseEntity<?> addNewProduct(@RequestParam("product") String product, 
                                           @RequestParam("image") MultipartFile file) throws IOException{
        ProductImage img = new ProductImage();
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        img.setImageData(ImageUtil.compressImage(file.getBytes()));
        Product p = null;
        try {
            p = objectMapper.readValue(product, Product.class);
            p.setProductImage(img);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
        Product saveProduct = this.productService.addProduct(p);
        return ResponseEntity.ok(saveProduct);
    }
    
    // Update existing product
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody Product product){
        Product updateProduct = this.productService.findProduct(id);
        updateProduct.setName(product.getName());
        updateProduct.setBrand(product.getBrand());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setSalt(product.getSalt());
        updateProduct.setTotalAvailable(product.getTotalAvailable());
        updateProduct.setPrice(product.getPrice());
        this.productService.addProduct(updateProduct);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    // Get product by ID
    @GetMapping("get-product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        Product product = this.productService.findProduct(id);
        ProductImage img =  new ProductImage();
        img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
        img.setImgId(product.getProductImage().getImgId());
        img.setName(product.getProductImage().getName());
        img.setType(product.getProductImage().getType());
        product.setProductImage(img);
        return ResponseEntity.ok(product);
    }
    
    // Get all products
    @GetMapping("/get/all-products")
    public ResponseEntity<?> getAllProducts(){
        List<Product> allProducts = this.productService.findAllProducts();
        allProducts.forEach(product -> {
            ProductImage img =  new ProductImage();
            img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
            img.setImgId(product.getProductImage().getImgId());
            img.setName(product.getProductImage().getName());
            img.setType(product.getProductImage().getType());
            product.setProductImage(img);
        });
        if(allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(allProducts);
        }
    }
    
    // Get products by name
    @GetMapping(value = {"/get/products/{name}"})
    public ResponseEntity<?> getProductByName(@PathVariable("name") String name,@PathVariable("name") String salt){
        List<Product> products = this.productService.findByNameOrSalt(name, salt);
        products.forEach(product -> {
            ProductImage img =  new ProductImage();
            img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
            img.setImgId(product.getProductImage().getImgId());
            img.setName(product.getProductImage().getName());
            img.setType(product.getProductImage().getType());
            product.setProductImage(img);
        });
        if(products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

    // Delete product by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        this.productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    // Set product availability
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/set-availability/product/{id}")
    public ResponseEntity<?> setAvailability(@PathVariable("id") Long id, @RequestBody Product product){
        Product updateProduct = this.productService.findProduct(id);
        updateProduct.setAvailable(product.isAvailable());
        this.productService.addProduct(updateProduct);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    // Get available products
    @GetMapping("/get/{name}")
    public ResponseEntity<?> getAvailable(@PathVariable("name") String name){
        List<Product> products = this.productService.findTrueProduct(name);
        if(products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

 // Add new combo product
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add/comboproduct")
    public ResponseEntity<?> addNewComboProduct(@RequestBody comboproduct comboProduct) {
        comboproduct savedComboProduct = productService.addComboProduct(comboProduct);
        return ResponseEntity.ok(savedComboProduct);
    }

    // Update existing combo product
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/comboproduct/{id}")
    public ResponseEntity<?> updateComboProduct(@PathVariable("id") Long id, @Valid @RequestBody comboproduct comboProduct) {
        comboproduct existingComboProduct = productService.findComboProduct(id);
        if (existingComboProduct != null) {
            existingComboProduct.setProduct1(comboProduct.getProduct1());
            existingComboProduct.setProduct2(comboProduct.getProduct2());
            existingComboProduct.setSize1(comboProduct.getSize1());
            existingComboProduct.setSize2(comboProduct.getSize2());
            productService.addComboProduct(existingComboProduct);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get combo product by ID
    @GetMapping("/get/comboproduct/{id}")
    public ResponseEntity<?> getComboProductById(@PathVariable("id") Long id) {
        comboproduct comboProduct = productService.findComboProduct(id);
        if (comboProduct != null) {
            return ResponseEntity.ok(comboProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get all combo products
    @GetMapping("/get/all-comboproducts")
    public ResponseEntity<?> getAllComboProducts() {
        List<comboproduct> comboProducts = productService.findAllComboProducts();
        return ResponseEntity.ok(comboProducts);
    }

    // Delete combo product by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/comboproduct/{id}")
    public ResponseEntity<?> deleteComboProduct(@PathVariable("id") Long id) {
        productService.deleteComboProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
