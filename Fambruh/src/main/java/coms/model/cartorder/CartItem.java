package coms.model.cartorder;

import coms.model.product.Product;
import coms.model.product.comboproduct;

public class CartItem {

    private Product product; 
    private comboproduct comboproduct;
    private int quantity;

    public CartItem() {
    }

    // Constructor for regular product
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Constructor for combo product
    public CartItem(comboproduct comboproduct, int quantity) {
        this.comboproduct = comboproduct;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public comboproduct getComboproduct() {
        return comboproduct;
    }

    public void setComboproduct(comboproduct comboproduct) {
        this.comboproduct = comboproduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
