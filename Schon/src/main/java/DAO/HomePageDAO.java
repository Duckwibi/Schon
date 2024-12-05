package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JoinTable.ProductImageJoinImage;
import JoinTable.ProductJoinProductCategory;
import Model.BlogCategory;
import Model.Image;
import Model.Menu;
import Model.Product;
import Model.ProductCategory;
import Model.ProductImage;
import OtherTable.BestSellerProduct;
import OtherTable.VoteOfReviewAVG;

public class HomePageDAO {
	
	public static ArrayList<ProductJoinProductCategory> getSliderList(){
		ArrayList<ProductJoinProductCategory> sliderList = new ArrayList<ProductJoinProductCategory>();
		String sql = "select top 3 * from tblProduct P join tblProductCategory PI on";
		sql	+= " P.ProductCategoryID = PI.ProductCategoryID where P.EnableSlider = 1";
		sql += " order by P.SliderOrder asc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				sliderList.add(new ProductJoinProductCategory(
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
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sliderList;
	}
	
	public static ArrayList<Product> getProductList(String sql){
		ArrayList<Product> productList = new ArrayList<Product>();
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	
	public static ArrayList<Product> getBannerList(){
		
		String sql = "select top 3 * from tblProduct where EnableBanner = 1 order by BannerOrder asc";
		
		return getProductList(sql);
	}
	
	public static ArrayList<Product> getFeaturedProductList(){
		
		String sql = "select top 10 * from tblProduct";
		
		return getProductList(sql);
	}
	
	public static ArrayList<Product> getLatestProductList(){
		
		String sql = "select top 10 * from tblProduct where IsNew = 1";
		
		return getProductList(sql);
	}
	
	public static ArrayList<Product> getHotSaleProductList(){
		
		String sql = "select top 10 * from tblProduct where Discount > 0";
		
		return getProductList(sql);
	}
	
	public static ArrayList<BestSellerProduct> getBestSellerProductList(){
		ArrayList<BestSellerProduct> bestSellerProductList = new ArrayList<BestSellerProduct>();
		String sql = "select top 8 * from tblProduct P join";
		sql	+= " (select ProductID, sum(cast(Quantity as float)) as 'SumOrderQuantity' from tblOrderDetail group by ProductID) B";
		sql += " on P.ProductID = B.ProductID order by B.SumOrderQuantity desc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				bestSellerProductList.add(new BestSellerProduct(
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
						rs.getInt(17),
						rs.getDouble(18)
				));
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bestSellerProductList;
	}
	
	public static ArrayList<ProductImageJoinImage> getProductImageJoinImageList(int levels){
		ArrayList<ProductImageJoinImage> productImageJoinImageList = new ArrayList<ProductImageJoinImage>();
		String sql = "select * from tblProductImage PI join tblImage I on PI.ImageID = I.ImageID where PI.Levels = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, levels);
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
