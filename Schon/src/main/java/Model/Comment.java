package Model;

import java.sql.Timestamp;

public class Comment {
	private int commentID;
	private Timestamp createdDate;
	private String message;
	private int blogID;
	private int customerID;
	public Comment() {
		super();
	}
	public Comment(int commentID, Timestamp createdDate, String message, int blogID, int customerID) {
		super();
		this.commentID = commentID;
		this.createdDate = createdDate;
		this.message = message;
		this.blogID = blogID;
		this.customerID = customerID;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getBlogID() {
		return blogID;
	}
	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}
