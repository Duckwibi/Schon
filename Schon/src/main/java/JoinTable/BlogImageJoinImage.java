package JoinTable;

import Model.BlogImage;
import Model.Image;

public class BlogImageJoinImage {
	private BlogImage blogImage;
	private Image image;
	public BlogImageJoinImage() {
		super();
	}
	public BlogImageJoinImage(BlogImage blogImage, Image image) {
		super();
		this.blogImage = blogImage;
		this.image = image;
	}
	public BlogImage getBlogImage() {
		return blogImage;
	}
	public void setBlogImage(BlogImage blogImage) {
		this.blogImage = blogImage;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
