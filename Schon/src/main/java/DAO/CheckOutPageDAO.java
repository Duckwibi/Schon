package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JoinTable.CartJoinProduct;
import Model.BlogCategory;
import Model.Cart;
import Model.DiscountCode;
import Model.Menu;
import Model.Product;
import Model.ProductCategory;
import Model.ShippingPrice;

public class CheckOutPageDAO {
	
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
	
	public static ShippingPrice getShippingPrice(String town, String city) {
		ShippingPrice shippingPrice = null;
		String sql = "select * from tblShippingPrice where Town = ? and City = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, town);
			ps.setString(2, city);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				shippingPrice = new ShippingPrice(
						rs.getString(1), 
						rs.getString(2), 
						rs.getDouble(3)
				);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shippingPrice;
	}
	
	public static DiscountCode getDiscountCode(String name) {
		DiscountCode discountCode = null;
		String sql = "select * from tblDiscountCode where Name = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				discountCode = new DiscountCode(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getTimestamp(3),
						rs.getInt(4)
				);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return discountCode;
	}
	
	public static ArrayList<CartJoinProduct> getCartJoinProductList(int customerID) {
		ArrayList<CartJoinProduct> cartJoinProductList = new ArrayList<CartJoinProduct>();
		String sql = "select * from tblCart C join tblProduct P";
		sql += " on C.ProductID = P.ProductID where C.CustomerID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				cartJoinProductList.add(new CartJoinProduct(
						new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3)),
						new Product(
								rs.getInt(4),
								rs.getString(5),
								rs.getInt(6),
								rs.getDouble(7),
								rs.getInt(8),
								rs.getString(9),
								rs.getString(10),
								rs.getInt(11),
								rs.getInt(12),
								rs.getString(13),
								rs.getString(14),
								rs.getInt(15),
								rs.getInt(16),
								rs.getInt(17),
								rs.getInt(18),
								rs.getInt(19)
						)
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cartJoinProductList;
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
	
	public static double getSubTotal(int customerID) {
		double subTotal = 0;
		String sql = "select sum((P.Price - P.Price * P.Discount / 100) * C.Quantity)";
		sql += " from tblCart C join tblProduct P on C.ProductID = P.ProductID where C.CustomerID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			subTotal = rs.getDouble(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return subTotal;
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
