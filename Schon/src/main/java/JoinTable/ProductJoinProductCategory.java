package JoinTable;

import Model.Product;
import Model.ProductCategory;

public class ProductJoinProductCategory {
	private Product product;
	private ProductCategory productCategory;
	public ProductJoinProductCategory() {
		super();
	}
	public ProductJoinProductCategory(Product product, ProductCategory productCategory) {
		super();
		this.product = product;
		this.productCategory = productCategory;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
}
