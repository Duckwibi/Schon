package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JoinTable.ProductImageJoinImage;
import JoinTable.ProductJoinProductCategory;
import JoinTable.ReviewJoinCustomer;
import Model.BlogCategory;
import Model.Customer;
import Model.Image;
import Model.Menu;
import Model.Product;
import Model.ProductCategory;
import Model.ProductImage;
import Model.Review;
import OtherTable.VoteOfReviewAVG;

public class ProductDetailPageDAO {
	public static ProductJoinProductCategory getProductJoinProductCategory(int productID) {
		ProductJoinProductCategory productJoinProductCategory = null;
		String sql = "select * from tblProduct P join tblProductCategory PC";
		sql	+= " on P.ProductCategoryID = PC.ProductCategoryID where P.ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				productJoinProductCategory = new ProductJoinProductCategory(
						new Product(
								rs.getInt(1),
								rs.getString(2),
								rs.getInt(3),
								rs.getDouble(4),
								rs.getInt(5),
								rs.getString(6),
								rs.getString(7),
								rs.getInt(8),
								rs.getInt(9),
								rs.getString(10),
								rs.getString(11),
								rs.getInt(12),
								rs.getInt(13),
								rs.getInt(14),
								rs.getInt(15),
								rs.getInt(16)
						),
						new ProductCategory(
								rs.getInt(17),
								rs.getString(18),
								rs.getInt(19)
						)
				);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productJoinProductCategory;
	}
	
	public static int getWishListCountOfProduct(int productID) {
		int wishListCountOfProduct = 0;
		String sql = "select count(*) from tblWishList where ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			wishListCountOfProduct = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wishListCountOfProduct;
	}
	
	public static int[] getReviewCountAndVoteAVG(int productID) {
		int[] reviewCountAndVoteAVG = {0, 0};
		String sql = "select count(*),";
		sql	+= " case when cast(round(avg(1.0 * Vote), 0) as int) is null then 0";
		sql	+= " else cast(round(avg(1.0 * Vote), 0) as int) end from tblReview where ProductID = ?";
			
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			reviewCountAndVoteAVG = new int[] {rs.getInt(1), rs.getInt(2)};
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reviewCountAndVoteAVG;
	}
	
	public static ArrayList<ProductImageJoinImage> getProductImageJoinImageList(int levels, int productID){
		ArrayList<ProductImageJoinImage> productImageJoinImageList = new ArrayList<ProductImageJoinImage>();
		String sql = "select * from tblProductImage PI join tblImage I";
		sql += " on PI.ImageID = I.ImageID where PI.Levels = ? and PI.ProductID = ? order by PI.Orders desc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, levels);
			ps.setInt(2, productID);
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
	
	public static ArrayList<ReviewJoinCustomer> getReviewJoinCustomerList(int productID){
		ArrayList<ReviewJoinCustomer> reviewJoinCustomerList = new ArrayList<ReviewJoinCustomer>();
		String sql = "select * from tblReview R join tblCustomer C on R.CustomerID = C.CustomerID";
		sql += " where R.ProductID = ? order by R.CreatedDate desc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				reviewJoinCustomerList.add(new ReviewJoinCustomer(
						new Review(
								rs.getInt(1),
								rs.getInt(2),
								rs.getInt(3),
								rs.getTimestamp(4),
								rs.getString(5)
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
		
		return reviewJoinCustomerList;
	}
	
	public static ArrayList<Product> getProductList(int productCategoryID, int productID){
		ArrayList<Product> productList = new ArrayList<Product>();
		String sql = "select top 5 * from tblProduct where ProductCategoryID = ? and ProductID <> ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productCategoryID);
			ps.setInt(2, productID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				productList.add(new Product(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getDouble(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getString(10),
						rs.getString(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}
	
	public static ArrayList<VoteOfReviewAVG> getVoteOfReviewAVGList(){
		ArrayList<VoteOfReviewAVG> voteOfReviewAVGList = new ArrayList<VoteOfReviewAVG>();
		String sql = "select ProductID, CAST(ROUND(AVG(1.0 * Vote), 0) as int) as 'Vote' from tblReview group by ProductID";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				voteOfReviewAVGList.add(new VoteOfReviewAVG(
						rs.getInt(1),
						rs.getInt(2)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return voteOfReviewAVGList;
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
