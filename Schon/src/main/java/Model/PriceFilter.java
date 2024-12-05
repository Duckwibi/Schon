package Model;

public class PriceFilter {
	private int priceFilterID;
	private String name;
	private double minPrice;
	private double maxPrice;
	private int orders;
	public PriceFilter() {
		super();
	}
	public PriceFilter(int priceFilterID, String name, double minPrice, double maxPrice, int orders) {
		super();
		this.priceFilterID = priceFilterID;
		this.name = name;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.orders = orders;
	}
	public int getPriceFilterID() {
		return priceFilterID;
	}
	public void setPriceFilterID(int priceFilterID) {
		this.priceFilterID = priceFilterID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
}
