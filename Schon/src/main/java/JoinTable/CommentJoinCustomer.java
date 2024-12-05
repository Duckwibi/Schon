package JoinTable;

import Model.Comment;
import Model.Customer;

public class CommentJoinCustomer {
	private Comment comment;
	private Customer customer;
	public CommentJoinCustomer() {
		super();
	}
	public CommentJoinCustomer(Comment comment, Customer customer) {
		super();
		this.comment = comment;
		this.customer = customer;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
