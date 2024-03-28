package coms.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import coms.configuration.ImageUtil;
import coms.model.product.Product;
import coms.model.product.ProductImage1;
import coms.model.product.ProductImage2;
import coms.model.product.ProductImage3;
import coms.model.product.ProductImageDetail;
import coms.model.product.ProductImageHover;
import coms.model.product.ProductImageMain;
import coms.model.product.comboproduct;
import coms.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
   
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add/product")
    public ResponseEntity<?> addNewProduct(@RequestParam("product") String productData,
                                           @RequestParam("mainImage") MultipartFile mainImageFile,
                                           @RequestParam("hoverImage") MultipartFile hoverImagefile,
                                           @RequestParam("detailImage") MultipartFile detailImagefile,
                                           @RequestParam("image1") MultipartFile Image1file,
                                           @RequestParam("image2") MultipartFile Image2file,
                                           @RequestParam("image3") MultipartFile Image3file) throws IOException {
        if (!mainImageFile.getContentType().startsWith("image/") && !mainImageFile.getContentType().equals("image/gif")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only images and GIFs are allowed for the main image.");
        }
        if (!hoverImagefile.getContentType().startsWith("image/") && !hoverImagefile.getContentType().equals("image/gif")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only images and GIFs are allowed for the additional image.");
        }
        if (!detailImagefile.getContentType().startsWith("image/") && !detailImagefile.getContentType().equals("image/gif")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only images and GIFs are allowed for the additional image.");
        }
        if (!Image1file.getContentType().startsWith("image/") && !Image1file.getContentType().equals("image/gif")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only images and GIFs are allowed for the additional image.");
        }
        if (!Image2file.getContentType().startsWith("image/") && !Image2file.getContentType().equals("image/gif")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only images and GIFs are allowed for the additional image.");
        }
        if (!Image3file.getContentType().startsWith("image/") && !Image3file.getContentType().equals("image/gif")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only images and GIFs are allowed for the additional image.");
        }

        ProductImageMain img1 = new ProductImageMain();
        img1.setName(mainImageFile.getOriginalFilename());
        img1.setType(mainImageFile.getContentType());
        img1.setImageData(ImageUtil.compressImage(mainImageFile.getBytes()));

        ProductImageHover img2 = new ProductImageHover();
        img2.setName(hoverImagefile.getOriginalFilename());
        img2.setType(hoverImagefile.getContentType());
        img2.setImageData(ImageUtil.compressImage(hoverImagefile.getBytes()));

        ProductImageDetail img3 = new ProductImageDetail();
        img3.setName(detailImagefile.getOriginalFilename());
        img3.setType(detailImagefile.getContentType());
        img3.setImageData(ImageUtil.compressImage(detailImagefile.getBytes()));

        ProductImage1 img4 = new ProductImage1();
        img4.setName(Image1file.getOriginalFilename());
        img4.setType(Image1file.getContentType());
        img4.setImageData(ImageUtil.compressImage(Image1file.getBytes()));

        ProductImage2 img5 = new ProductImage2();
        img5.setName(Image2file.getOriginalFilename());
        img5.setType(Image2file.getContentType());
        img5.setImageData(ImageUtil.compressImage(Image2file.getBytes()));

        ProductImage3 img6 = new ProductImage3();
        img6.setName(Image3file.getOriginalFilename());
        img6.setType(Image3file.getContentType());
        img6.setImageData(ImageUtil.compressImage(Image3file.getBytes()));

        Product product = null;

        try {
            product = objectMapper.readValue(productData, Product.class);
            product.setMainImage(img1);
            product.setHoverImage(img2);
            product.setDetailImage(img3);
            product.setImage1(img4);
            product.setImage2(img5);
            product.setImage3(img6);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }
        Product addedProduct = this.productService.addProduct(product);
        return ResponseEntity.ok(addedProduct);
    }


	//update existing product
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/update/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,@Valid @RequestBody Product product){
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
	
	//find product by id
	@GetMapping("get-product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
		Product product = this.productService.findProduct(id);
		ProductImageMain img1 =  new ProductImageMain();
		img1.setImageData(ImageUtil.decompressImage(product.getMainImage().getImageData()));
		img1.setImgId(product.getMainImage().getImgId());
		img1.setName(product.getMainImage().getName());
		img1.setType(product.getMainImage().getType());
		product.setMainImage(img1);
		
		ProductImage1 img4 =  new ProductImage1();
		img4.setImageData(ImageUtil.decompressImage(product.getImage1().getImageData()));
		img4.setImgId(product.getImage1().getImgId());
		img4.setName(product.getImage1().getName());
		img4.setType(product.getImage1().getType());
		product.setImage1(img4);
		
		ProductImage2 img5 =  new ProductImage2();
		img5.setImageData(ImageUtil.decompressImage(product.getImage2().getImageData()));
		img5.setImgId(product.getImage2().getImgId());
		img5.setName(product.getImage2().getName());
		img5.setType(product.getImage2().getType());
		product.setImage2(img5);
		
		ProductImage3 img6 =  new ProductImage3();
		img6.setImageData(ImageUtil.decompressImage(product.getImage3().getImageData()));
		img6.setImgId(product.getImage3().getImgId());
		img6.setName(product.getImage3().getName());
		img6.setType(product.getImage3().getType());
		product.setImage3(img6);
		return ResponseEntity.ok(product);
	}
	
    

	//find all products
	@GetMapping("/get/all-products")
	public ResponseEntity<?> getAllProducts(){
		List<Product> allProducts = this.productService.findAllProducts();
		allProducts.forEach(product -> {
			ProductImageMain img1 =  new ProductImageMain();
			img1.setImageData(ImageUtil.decompressImage(product.getMainImage().getImageData()));
			img1.setImgId(product.getMainImage().getImgId());
			img1.setName(product.getMainImage().getName());
			img1.setType(product.getMainImage().getType());
			product.setMainImage(img1);
			
	
		});
		if(allProducts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(allProducts);
		}
	}


    // Delete product by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        this.productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
    
    // Set product availability
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/set-availability/product/{id}")
    public ResponseEntity<?> setAvailability(@PathVariable("id") Long id, @RequestBody Product product){
        Product updateProduct = this.productService.findProduct(id);
        if (updateProduct == null) {
            return ResponseEntity.notFound().build();
        }
        updateProduct.setAvailable(product.isAvailable());
        this.productService.addProduct(updateProduct);
        return ResponseEntity.ok().build();
    }
    
    // Get available products
    @GetMapping("/get/{name}")
    public ResponseEntity<?> getAvailable(@PathVariable("name") String name){
        List<Product> products = this.productService.findTrueProduct(name);
        if(products.isEmpty()) {
            return ResponseEntity.notFound().build();
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
            productService.addComboProduct(existingComboProduct);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get combo product by ID
    @GetMapping("/get/comboproduct/{id}")
    public ResponseEntity<?> getComboProductById(@PathVariable("id") Long id) {
        comboproduct comboProduct = productService.findComboProduct(id);
        if (comboProduct != null) {
            return ResponseEntity.ok(comboProduct);
        } else {
            return ResponseEntity.notFound().build();
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
        return ResponseEntity.ok().build();
    }
}

