package coms.model.cartorder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import coms.model.product.Product;

@Entity
public class wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wishid;

    @OneToOne
    private Product product;

    public wishlist() {
        // Default constructor
    }

    public wishlist(Product product) {
        this.wishid = wishid;
        this.product = product;
    }

    public int getId() {
        return wishid;
    }

    public void setId(int id) {
        this.wishid = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
