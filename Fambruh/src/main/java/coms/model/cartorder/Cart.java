package coms.model.cartorder;
import coms.model.product.*;

import coms.model.user.User;





import javax.persistence.*;

@Entity
public class Cart {
	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long cartId;
	    @OneToOne
	    private Product product;
	    @OneToOne
	    private User user;
		
	    
	    public Cart() {
			super();
		}


		public Cart(Product product, User user) {
			super();
			this.product = product;
			this.user = user;
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
