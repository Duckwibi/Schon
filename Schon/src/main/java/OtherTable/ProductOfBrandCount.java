package OtherTable;

public class ProductOfBrandCount {
	private int brandID;
	private int count;
	public ProductOfBrandCount() {
		super();
	}
	public ProductOfBrandCount(int brandID, int count) {
		super();
		this.brandID = brandID;
		this.count = count;
	}
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
