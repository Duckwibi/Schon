package JoinTable;

import Model.Product;
import Model.WishList;

public class WishListJoinProduct {
	private WishList wishList;
	private Product product;
	public WishListJoinProduct() {
		super();
	}
	public WishListJoinProduct(WishList wishList, Product product) {
		super();
		this.wishList = wishList;
		this.product = product;
	}
	public WishList getWishList() {
		return wishList;
	}
	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
