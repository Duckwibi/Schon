package Model;

public class Product {
	private int productID;
	private String name;
	private int quantity;
	private double price;
	private int discount;
	private String infomation;
	private String description;
	private int isNew;
	private int enableSlider;
	private String sliderTitle;
	private String sliderDescription;
	private int sliderOrder;
	private int enableBanner;
	private int bannerOrder;
	private int brandID;
	private int productCategoryID;
	public Product() {
		super();
	}
	public Product(int productID, String name, int quantity, double price, int discount, String infomation,
			String description, int isNew, int enableSlider, String sliderTitle, String sliderDescription,
			int sliderOrder, int enableBanner, int bannerOrder, int brandID, int productCategoryID) {
		super();
		this.productID = productID;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.infomation = infomation;
		this.description = description;
		this.isNew = isNew;
		this.enableSlider = enableSlider;
		this.sliderTitle = sliderTitle;
		this.sliderDescription = sliderDescription;
		this.sliderOrder = sliderOrder;
		this.enableBanner = enableBanner;
		this.bannerOrder = bannerOrder;
		this.brandID = brandID;
		this.productCategoryID = productCategoryID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getInfomation() {
		return infomation;
	}
	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public int getEnableSlider() {
		return enableSlider;
	}
	public void setEnableSlider(int enableSlider) {
		this.enableSlider = enableSlider;
	}
	public String getSliderTitle() {
		return sliderTitle;
	}
	public void setSliderTitle(String sliderTitle) {
		this.sliderTitle = sliderTitle;
	}
	public String getSliderDescription() {
		return sliderDescription;
	}
	public void setSliderDescription(String sliderDescription) {
		this.sliderDescription = sliderDescription;
	}
	public int getSliderOrder() {
		return sliderOrder;
	}
	public void setSliderOrder(int sliderOrder) {
		this.sliderOrder = sliderOrder;
	}
	public int getEnableBanner() {
		return enableBanner;
	}
	public void setEnableBanner(int enableBanner) {
		this.enableBanner = enableBanner;
	}
	public int getBannerOrder() {
		return bannerOrder;
	}
	public void setBannerOrder(int bannerOrder) {
		this.bannerOrder = bannerOrder;
	}
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	public int getProductCategoryID() {
		return productCategoryID;
	}
	public void setProductCategoryID(int productCategoryID) {
		this.productCategoryID = productCategoryID;
	}
}
