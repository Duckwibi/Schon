package Model;

public class WishList {
	private int customerID;
	private int productID;
	public WishList() {
		super();
	}
	public WishList(int customerID, int productID) {
		super();
		this.customerID = customerID;
		this.productID = productID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
}
