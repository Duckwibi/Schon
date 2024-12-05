package Model;

public class ShippingPrice {
	private String town;
	private String city;
	private double price;
	public ShippingPrice() {
		super();
	}
	public ShippingPrice(String town, String city, double price) {
		super();
		this.town = town;
		this.city = city;
		this.price = price;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
