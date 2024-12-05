package JoinTable;

import Model.Cart;
import Model.Product;

public class CartJoinProduct {
	private Cart cart;
	private Product product;
	public CartJoinProduct() {
		super();
	}
	public CartJoinProduct(Cart cart, Product product) {
		super();
		this.cart = cart;
		this.product = product;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
