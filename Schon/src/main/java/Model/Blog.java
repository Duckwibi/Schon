package Model;

import java.sql.Timestamp;

public class Blog {
	private int blogID;
	private Timestamp createdDate;
	private String title;
	private String content;
	private int blogCategoryID;
	public Blog() {
		super();
	}
	public Blog(int blogID, Timestamp createdDate, String title, String content, int blogCategoryID) {
		super();
		this.blogID = blogID;
		this.createdDate = createdDate;
		this.title = title;
		this.content = content;
		this.blogCategoryID = blogCategoryID;
	}
	public int getBlogID() {
		return blogID;
	}
	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBlogCategoryID() {
		return blogCategoryID;
	}
	public void setBlogCategoryID(int blogCategoryID) {
		this.blogCategoryID = blogCategoryID;
	}
}
