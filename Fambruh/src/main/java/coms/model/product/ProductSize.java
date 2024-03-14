package coms.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import coms.repository.Size;

@Entity
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sizeId;

    @Enumerated(EnumType.STRING)
    private Size sizeName;

    @NotNull(message = "isAvailable cannot be null")
    private boolean isAvailable;

    @ManyToOne
    private Product product;

    // Constructors, getters, setters...

    public ProductSize() {
        // Default constructor for JPA
    }

    public ProductSize(Long sizeId, Size sizeName, boolean isAvailable, Product product) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.isAvailable = isAvailable;
        this.product = product;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public Size getSizeName() {
        return sizeName;
    }

    public void setSizeName(Size sizeName) {
        this.sizeName = sizeName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
