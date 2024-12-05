package Model;

public class BlogImage {
	private int blogID;
	private int imageID;
	private int levels;
	public BlogImage() {
		super();
	}
	public BlogImage(int blogID, int imageID, int levels) {
		super();
		this.blogID = blogID;
		this.imageID = imageID;
		this.levels = levels;
	}
	public int getBlogID() {
		return blogID;
	}
	public void setBlogID(int blogID) {
		this.blogID = blogID;
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
}
