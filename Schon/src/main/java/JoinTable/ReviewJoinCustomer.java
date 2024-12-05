package JoinTable;

import Model.Customer;
import Model.Review;

public class ReviewJoinCustomer {
	private Review review;
	private Customer customer;
	public ReviewJoinCustomer() {
		super();
	}
	public ReviewJoinCustomer(Review review, Customer customer) {
		super();
		this.review = review;
		this.customer = customer;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
