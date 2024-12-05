package Model;

public class ProductImage {
	private int productID;
	private int imageID;
	private int levels;
	private int orders;
	public ProductImage() {
		super();
	}
	public ProductImage(int productID, int imageID, int levels, int orders) {
		super();
		this.productID = productID;
		this.imageID = imageID;
		this.levels = levels;
		this.orders = orders;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}	
}
