package coms.model.product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pqid;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    private int quantity;

    public ProductQuantity() {

    }

    public ProductQuantity(Product product, int quantity) {
     
        this.product = product;
        this.quantity = quantity;
    }

    public Long getPqid() {
        return pqid;
    }

    public void setPqid(Long pqid) {
        this.pqid = pqid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
