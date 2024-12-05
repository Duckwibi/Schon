package OtherTable;

public class CommentOfBlogCount {
	private int blogID;
	private int count;
	public CommentOfBlogCount() {
		super();
	}
	public CommentOfBlogCount(int blogID, int count) {
		super();
		this.blogID = blogID;
		this.count = count;
	}
	public int getBlogID() {
		return blogID;
	}
	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
