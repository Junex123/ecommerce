package coms.model.product;

import javax.persistence.*;

@Entity
public class comboproduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product1;

    @OneToOne
    private Product product2;

    // Sizes for product1 and product2
    private String size1;
    private String size2;

    // Additional fields, constructors, getters, and setters...

    public comboproduct() {
        // Default constructor for JPA
    }

    public comboproduct(Product product1, Product product2, String size1, String size2) {
        this.product1 = product1;
        this.product2 = product2;
        this.size1 = size1;
        this.size2 = size2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct1() {
        return product1;
    }

    public void setProduct1(Product product1) {
        this.product1 = product1;
    }

    public Product getProduct2() {
        return product2;
    }

    public void setProduct2(Product product2) {
        this.product2 = product2;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public String getSize2() {
        return size2;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }
}
