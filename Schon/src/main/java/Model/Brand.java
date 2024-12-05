package Model;

public class Brand {
	private int brandID;
	private String name;
	public Brand() {
		super();
	}
	public Brand(int brandID, String name) {
		super();
		this.brandID = brandID;
		this.name = name;
	}
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
