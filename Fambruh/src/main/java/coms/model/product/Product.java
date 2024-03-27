package coms.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import coms.model.product.*;
import coms.repository.Size;



@Entity

@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "brand cannot be blank")
    private String brand;
    
    @Column(length = 2000)
    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotBlank(message = "salt cannot be blank")
    private String salt;

    @NotNull(message = "available cannot be null")
    private int totalAvailable;

    @NotNull(message = "price cannot be null")
    private Double price;

    private Double productDiscountedPrice;
    
    @NotNull(message = "isAvailable cannot be null")
    private boolean isAvailable;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "product_size_table",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "size_id")
            }
    )
    private Set<ProductSize> sizes;

    @OneToOne(cascade = CascadeType.ALL)
 	@JsonManagedReference
 	private ProductImageMain mainImage;
    
    @OneToOne(cascade = CascadeType.ALL)
  	@JsonManagedReference
  	private ProductImageHover hoverImage;
     
    @OneToOne(cascade = CascadeType.ALL)
  	@JsonManagedReference
  	private ProductImage1 image1;
     
    @OneToOne(cascade = CascadeType.ALL)
  	@JsonManagedReference
  	private ProductImage2 image2;
     
    @OneToOne(cascade = CascadeType.ALL)
  	@JsonManagedReference
  	private ProductImage3 image3;
     
    @OneToOne(cascade = CascadeType.ALL)
  	@JsonManagedReference
  	private ProductImageDetail detailImage;
    
    

	public Product() {
		super();
	}



	public Product(Long pid, @NotBlank(message = "name cannot be blank") String name,
			@NotBlank(message = "brand cannot be blank") String brand,
			@NotBlank(message = "description cannot be blank") String description,
			@NotBlank(message = "salt cannot be blank") String salt,
			@NotNull(message = "available cannot be null") int totalAvailable,
			@NotNull(message = "price cannot be null") Double price, Double productDiscountedPrice,
			@NotNull(message = "isAvailable cannot be null") boolean isAvailable, Set<ProductSize> sizes,
			ProductImageMain mainImage, ProductImageHover hoverImage, ProductImage1 image1, ProductImage2 image2,
			ProductImage3 image3, ProductImageDetail detailImage) {
		super();
		this.pid = pid;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.salt = salt;
		this.totalAvailable = totalAvailable;
		this.price = price;
		this.productDiscountedPrice = productDiscountedPrice;
		this.isAvailable = isAvailable;
		this.sizes = sizes;
		this.mainImage = mainImage;
		this.hoverImage = hoverImage;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.detailImage = detailImage;
	}



	public Long getPid() {
		return pid;
	}



	public void setPid(Long pid) {
		this.pid = pid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getSalt() {
		return salt;
	}



	public void setSalt(String salt) {
		this.salt = salt;
	}



	public int getTotalAvailable() {
		return totalAvailable;
	}



	public void setTotalAvailable(int totalAvailable) {
		this.totalAvailable = totalAvailable;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Double getProductDiscountedPrice() {
		return productDiscountedPrice;
	}



	public void setProductDiscountedPrice(Double productDiscountedPrice) {
		this.productDiscountedPrice = productDiscountedPrice;
	}



	public boolean isAvailable() {
		return isAvailable;
	}



	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}



	public Set<ProductSize> getSizes() {
		return sizes;
	}



	public void setSizes(Set<ProductSize> sizes) {
		this.sizes = sizes;
	}



	public ProductImageMain getMainImage() {
		return mainImage;
	}



	public void setMainImage(ProductImageMain mainImage) {
		this.mainImage = mainImage;
	}



	public ProductImageHover getHoverImage() {
		return hoverImage;
	}



	public void setHoverImage(ProductImageHover hoverImage) {
		this.hoverImage = hoverImage;
	}



	public ProductImage1 getImage1() {
		return image1;
	}



	public void setImage1(ProductImage1 image1) {
		this.image1 = image1;
	}



	public ProductImage2 getImage2() {
		return image2;
	}



	public void setImage2(ProductImage2 image2) {
		this.image2 = image2;
	}



	public ProductImage3 getImage3() {
		return image3;
	}



	public void setImage3(ProductImage3 image3) {
		this.image3 = image3;
	}



	public ProductImageDetail getDetailImage() {
		return detailImage;
	}



	public void setDetailImage(ProductImageDetail detailImage) {
		this.detailImage = detailImage;
	}








 







   
}
