package coms.model.cartorder;
import coms.model.product.*;

import coms.model.user.User;





import javax.persistence.*;

@Entity
public class CartItem {
	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long cartId;
	    @OneToOne
	    private Product product;
	    @OneToOne
	    private User user;
		private int quantity;
		  @OneToOne
		    private comboproduct comboproduct;
	    public CartItem() {
			super();
		}


		public CartItem(comboproduct comboproduct,User user) {
			super();
			this.user = user;
			this.comboproduct = comboproduct;
		}


		


	


	

		public CartItem(Product product, User user, int quantity) {
			super();
			this.product = product;
			this.user = user;
			this.quantity = quantity;
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


		public Long getCartId() {
			return cartId;
		}


		public void setCartId(Long cartId) {
			this.cartId = cartId;
		}


		public Product getProduct() {
			return product;
		}


		public void setProduct(Product product) {
			this.product = product;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


	
	    

}
