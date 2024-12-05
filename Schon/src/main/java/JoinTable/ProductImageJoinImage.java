package JoinTable;

import Model.Image;
import Model.ProductImage;

public class ProductImageJoinImage {
	private ProductImage productImage;
	private Image image;
	public ProductImageJoinImage() {
		super();
	}
	public ProductImageJoinImage(ProductImage productImage, Image image) {
		super();
		this.productImage = productImage;
		this.image = image;
	}
	public ProductImage getProductImage() {
		return productImage;
	}
	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
