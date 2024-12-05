package Model;

public class Menu {
	private int menuID;
	private String name;
	private int levels;
	private int orders;
	private int parentID;
	private String link;
	private int enableLink;
	public Menu() {
		super();
	}
	public Menu(int menuID, String name, int levels, int orders, int parentID, String link, int enableLink) {
		super();
		this.menuID = menuID;
		this.name = name;
		this.levels = levels;
		this.orders = orders;
		this.parentID = parentID;
		this.link = link;
		this.enableLink = enableLink;
	}
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getEnableLink() {
		return enableLink;
	}
	public void setEnableLink(int enableLink) {
		this.enableLink = enableLink;
	}
}
