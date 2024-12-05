package Model;

import java.sql.Timestamp;

public class Review {
	private int customerID;
	private int productID;
	private int vote;
	private Timestamp createdDate;
	private String message;
	public Review() {
		super();
	}
	public Review(int customerID, int productID, int vote, Timestamp createdDate, String message) {
		super();
		this.customerID = customerID;
		this.productID = productID;
		this.vote = vote;
		this.createdDate = createdDate;
		this.message = message;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
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
}
