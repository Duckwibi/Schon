package Model;


public class BlogCategory {
	private int blogCategoryID;
	private String name;
	private int menuID;
	
	public BlogCategory() {
		super();
	}

	public BlogCategory(int blogCategoryID, String name, int menuID) {
		super();
		this.blogCategoryID = blogCategoryID;
		this.name = name;
		this.menuID = menuID;
	}

	public int getBlogCategoryID() {
		return blogCategoryID;
	}

	public void setBlogCategoryID(int blogCategoryID) {
		this.blogCategoryID = blogCategoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
}
