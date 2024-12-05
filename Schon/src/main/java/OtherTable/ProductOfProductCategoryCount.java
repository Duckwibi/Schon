package OtherTable;

public class ProductOfProductCategoryCount {
	private int productCategoryID;
	private int count;
	public ProductOfProductCategoryCount() {
		super();
	}
	public ProductOfProductCategoryCount(int productCategoryID, int count) {
		super();
		this.productCategoryID = productCategoryID;
		this.count = count;
	}
	public int getProductCategoryID() {
		return productCategoryID;
	}
	public void setProductCategoryID(int productCategoryID) {
		this.productCategoryID = productCategoryID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
