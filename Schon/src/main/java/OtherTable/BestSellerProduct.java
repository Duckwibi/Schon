package OtherTable;

import Model.Product;

public class BestSellerProduct {
	private Product product;
	private int productID;
	private double sumOrderQuantity;
	public BestSellerProduct() {
		super();
	}
	public BestSellerProduct(Product product, int productID, double sumOrderQuantity) {
		super();
		this.product = product;
		this.productID = productID;
		this.sumOrderQuantity = sumOrderQuantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public double getSumOrderQuantity() {
		return sumOrderQuantity;
	}
	public void setSumOrderQuantity(double sumOrderQuantity) {
		this.sumOrderQuantity = sumOrderQuantity;
	}
}
