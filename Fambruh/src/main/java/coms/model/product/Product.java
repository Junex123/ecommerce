package coms.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    @JsonManagedReference
    private List<ProductImage> images;

    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "pid"))
    private Set<ProductSize> sizes;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private ProductImage productImage;

    public Product() {
        // Default constructor for JPA
    }

    // Constructor with all fields (excluding collections) for convenience
    public Product(Long pid, String name, String brand, String description, String salt, int totalAvailable,
                   Double price, boolean isAvailable, List<ProductImage> images, Set<ProductSize> sizes,
                   ProductImage productImage) {
        this.pid = pid;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.salt = salt;
        this.totalAvailable = totalAvailable;
        this.price = price;
        this.isAvailable = isAvailable;
        this.images = images;
        this.sizes = sizes;
        this.productImage = productImage;
    }

    // Getter and setter methods for each field...

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

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Set<ProductSize> getSizes() {
        return sizes;
    }

    public void setSizes(Set<ProductSize> sizes) {
        this.sizes = sizes;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }
}
