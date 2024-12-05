package Model;

import java.sql.Timestamp;

public class Contact {
	private int contactID;
	private Timestamp createdDate;
	private String name;
	private String email;
	private String message;
	public Contact() {
		super();
	}
	public Contact(int contactID, Timestamp createdDate, String name, String email, String message) {
		super();
		this.contactID = contactID;
		this.createdDate = createdDate;
		this.name = name;
		this.email = email;
		this.message = message;
	}
	public int getContactID() {
		return contactID;
	}
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
