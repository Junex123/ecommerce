package coms.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotBlank(message = "salt cannot be blank")
    private String salt;

    @NotNull(message = "available cannot be null")
    private int totalAvailable;

    @NotNull(message = "price cannot be null")
    private Double price;

    @NotNull(message = "isAvailable cannot be null")
    private boolean isAvailable;

    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "pid"))
    @Enumerated(EnumType.STRING)
    private Set<Size> sizes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            }
    )
    private Set<ProductImage> productImages;
    
    
    
    public Product() {
        // Default constructor for JPA
    }







    public Product(Long pid, @NotBlank(message = "name cannot be blank") String name,
            @NotBlank(message = "brand cannot be blank") String brand,
            @NotBlank(message = "description cannot be blank") String description,
            @NotBlank(message = "salt cannot be blank") String salt,
            @NotNull(message = "available cannot be null") int totalAvailable,
            @NotNull(message = "price cannot be null") Double price,
            @NotNull(message = "isAvailable cannot be null") boolean isAvailable,
            Set<Size> sizes, Set<ProductImage> productImages) {
        super();
        this.pid = pid;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.salt = salt;
        this.totalAvailable = totalAvailable;
        this.price = price;
        this.isAvailable = isAvailable;
        
        this.sizes = sizes;
        this.productImages = productImages;
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



	public boolean isAvailable() {
		return isAvailable;
	}



	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}








	public Set<Size> getSizes() {
		return sizes;
	}







	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}







	public Set<ProductImage> getProductImages() {
		return productImages;
	}



	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

   
}
