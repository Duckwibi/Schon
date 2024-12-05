package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JoinTable.ProductImageJoinImage;
import JoinTable.WishListJoinProduct;
import Model.BlogCategory;
import Model.Image;
import Model.Menu;
import Model.Product;
import Model.ProductCategory;
import Model.ProductImage;
import Model.WishList;

public class WishListPageDAO {
	
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
	
	public static ArrayList<WishListJoinProduct> getWishListListJoinProductList(int customerID) {
		ArrayList<WishListJoinProduct> wishListListJoinProductList = new ArrayList<WishListJoinProduct>();
		String sql = "select * from tblWishList W join tblProduct P";
		sql += " on W.ProductID = P.ProductID where W.CustomerID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				wishListListJoinProductList.add(new WishListJoinProduct(
						new WishList(rs.getInt(1), rs.getInt(2)),
						new Product(
								rs.getInt(3),
								rs.getString(4),
								rs.getInt(5),
								rs.getDouble(6),
								rs.getInt(7),
								rs.getString(8),
								rs.getString(9),
								rs.getInt(10),
								rs.getInt(11),
								rs.getString(12),
								rs.getString(13),
								rs.getInt(14),
								rs.getInt(15),
								rs.getInt(16),
								rs.getInt(17),
								rs.getInt(18)
						)
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wishListListJoinProductList;
	}
	
	public static ArrayList<ProductImageJoinImage> getProductImageJoinImageList(){
		ArrayList<ProductImageJoinImage> productImageJoinImageList = new ArrayList<ProductImageJoinImage>();
		String sql = "select * from tblProductImage PI join tblImage I on PI.ImageID = I.ImageID where PI.Levels = 3";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				productImageJoinImageList.add(new ProductImageJoinImage(
						new ProductImage(
								rs.getInt(1),
								rs.getInt(2),
								rs.getInt(3),
								rs.getInt(4)
						), 
						new Image(
								rs.getInt(5), 
								rs.getString(6)
						)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productImageJoinImageList;
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
						rs.getInt(3)));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return blogCategoryList;
	}
}
