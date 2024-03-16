package coms.model.cartorder;

import javax.persistence.*;


import coms.model.product.Product;
import coms.model.user.User;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wishid;

    @ManyToOne
    private User user;

    @OneToOne
    private Product product;

	public Wishlist() {
		super();
	}

	public Wishlist(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
	}

	public int getWishid() {
		return wishid;
	}

	public void setWishid(int wishid) {
		this.wishid = wishid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    // Constructors, getters, and setters
}
