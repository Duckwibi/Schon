package Model;

public class ProductCategory {
	private int productCategoryID;
	private String name;
	private int menuID;
	public ProductCategory() {
		super();
	}
	public ProductCategory(int productCategoryID, String name, int menuID) {
		super();
		this.productCategoryID = productCategoryID;
		this.name = name;
		this.menuID = menuID;
	}
	public int getProductCategoryID() {
		return productCategoryID;
	}
	public void setProductCategoryID(int productCategoryID) {
		this.productCategoryID = productCategoryID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
}
