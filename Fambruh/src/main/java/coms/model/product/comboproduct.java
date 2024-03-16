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
    // Additional fields, constructors, getters, and setters...

    public comboproduct() {
        // Default constructor for JPA
    }

    public comboproduct(Product product1, Product product2) {
        this.product1 = product1;
        this.product2 = product2;
    
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


}
