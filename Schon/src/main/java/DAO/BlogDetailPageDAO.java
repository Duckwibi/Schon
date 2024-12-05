package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JoinTable.BlogImageJoinImage;
import JoinTable.BlogJoinBlogCategory;
import JoinTable.CommentJoinCustomer;
import Model.Blog;
import Model.BlogCategory;
import Model.BlogImage;
import Model.Comment;
import Model.Customer;
import Model.Image;
import Model.Menu;
import Model.ProductCategory;

public class BlogDetailPageDAO {
	
	public static BlogJoinBlogCategory getBlogJoinBlogCategory(int blogID) {
		BlogJoinBlogCategory blogJoinBlogCategory = null;
		String sql = "select * from tblBlog B join tblBlogCategory BC";
		sql	+= " on B.BlogCategoryID = BC.BlogCategoryID where B.BlogID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, blogID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				blogJoinBlogCategory = new BlogJoinBlogCategory(
						new Blog(
								rs.getInt(1),
								rs.getTimestamp(2), 
								rs.getString(3),
								rs.getString(4), 
								rs.getInt(5)
						),
						new BlogCategory(
								rs.getInt(6),
								rs.getString(7), 
								rs.getInt(8)
						)
			);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return blogJoinBlogCategory;
	}
	
	public static String getBlogImageLink(int blogID) {
		String link = "";
		String sql = "select I.link from tblBlogImage BI join tblImage I";
		sql += " on BI.ImageID = I.ImageID where BI.Levels = 3 and BI.BlogID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, blogID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				link = rs.getString(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return link;
	}
	
	public static ArrayList<CommentJoinCustomer> getCommentJoinCustomerList(int blogID) {
		ArrayList<CommentJoinCustomer> commentJoinCustomerList = new ArrayList<CommentJoinCustomer>();
		String sql = "select * from tblComment CO join tblCustomer CU";
		sql	+= " on CO.CustomerID = CU.CustomerID where CO.BlogID = ?";
		sql += " order by CO.CreatedDate desc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, blogID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				commentJoinCustomerList.add(new CommentJoinCustomer(
						new Comment(
								rs.getInt(1), 
								rs.getTimestamp(2), 
								rs.getString(3), 
								rs.getInt(4),
								rs.getInt(5)
						),
						new Customer(
								rs.getInt(6),
								rs.getString(7),
								rs.getString(8),
								rs.getString(9),
								rs.getString(10),
								rs.getString(11),
								rs.getString(12),
								rs.getString(13)
						)
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return commentJoinCustomerList;
	}
	
	public static ArrayList<BlogCategory> getBlogCategoryList(){
		ArrayList<BlogCategory> blogCategoryList = new ArrayList<BlogCategory>();
		String sql = "select * from tblBlogCategory";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				blogCategoryList.add(new BlogCategory(
						rs.getInt(1),
						rs.getString(2), 
						rs.getInt(3)
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return blogCategoryList;
	}
	
	public static ArrayList<Blog> getLatestBlogList(int blogCategoryID){
		ArrayList<Blog> latestBlogList = new ArrayList<Blog>();
		String sql = "select top 4 * from tblBlog where BlogCategoryID = ? order by CreatedDate desc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, blogCategoryID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				latestBlogList.add(new Blog(
						rs.getInt(1),
						rs.getTimestamp(2), 
						rs.getString(3),
						rs.getString(4), 
						rs.getInt(5)
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return latestBlogList;
	}
	
	public static ArrayList<BlogImageJoinImage> getBlogImageJoinImageList(int levels){
		ArrayList<BlogImageJoinImage> blogImageJoinImageList = new ArrayList<BlogImageJoinImage>();
		String sql = "select * from tblBlogImage BI join tblImage I on BI.ImageID = I.ImageID";
		sql += " where BI.Levels = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, levels);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				blogImageJoinImageList.add(new BlogImageJoinImage(
						new BlogImage(
								rs.getInt(1),
								rs.getInt(2),
								rs.getInt(3)
						),
						new Image(
								rs.getInt(4),
								rs.getString(5)
						)
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return blogImageJoinImageList;
	}
	
	public static ArrayList<Menu> getMenuList(){
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		String sql = "select * from tblMenu order by Orders asc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				menuList.add(new Menu(
						rs.getInt(1),
						rs.getString(2), 
						rs.getInt(3), 
						rs.getInt(4), 
						rs.getInt(5), 
						rs.getString(6), 
						rs.getInt(7)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return menuList;
	}
	
	public static ArrayList<ProductCategory> getProductCategoryList(){
		ArrayList<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		String sql = "select * from tblProductCategory";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				productCategoryList.add(new ProductCategory(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productCategoryList;
	}
	
	
	public static boolean checkCustomerID(int customerID) {
		boolean check = true;
		String sql = "select count(*) from tblCustomer where CustomerID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			check = rs.getInt(1) == 0 ? false : true;
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static int getCartCount(int customerID) {
		int cartCount = 0;
		String sql = "select count(*) from tblCart where CustomerID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			cartCount = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cartCount;
	}
	
	public static int getWishListCount(int customerID) {
		int wishListCount = 0;
		String sql = "select count(*) from tblWishList where CustomerID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			wishListCount = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wishListCount;
	}
}