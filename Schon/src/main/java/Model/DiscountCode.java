package Model;

import java.sql.Timestamp;

public class DiscountCode {
	private int discountCodeID;
	private String name;
	private Timestamp endDate;
	private int discount;
	public DiscountCode() {
		super();
	}
	public DiscountCode(int discountCodeID, String name, Timestamp endDate, int discount) {
		super();
		this.discountCodeID = discountCodeID;
		this.name = name;
		this.endDate = endDate;
		this.discount = discount;
	}
	public int getDiscountCodeID() {
		return discountCodeID;
	}
	public void setDiscountCodeID(int discountCodeID) {
		this.discountCodeID = discountCodeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
