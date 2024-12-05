package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JoinTable.ProductImageJoinImage;
import Model.BlogCategory;
import Model.Brand;
import Model.Image;
import Model.Menu;
import Model.PriceFilter;
import Model.Product;
import Model.ProductCategory;
import Model.ProductImage;

import OtherTable.ProductOfBrandCount;
import OtherTable.ProductOfProductCategoryCount;
import OtherTable.VoteOfReviewAVG;


public class ProductPageDAO {
	
	public static boolean checkBrandID(int brandID) {
		boolean check = true;
		String sql = "select count(*) from tblBrand where BrandID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, brandID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			check = rs.getInt(1) == 0 ? false : true; 
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static PriceFilter getPriceFilter(int priceFilterID) {
		PriceFilter priceFilter = null;
		String sql = "select * from tblPriceFilter where PriceFilterID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, priceFilterID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				priceFilter = new PriceFilter(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getDouble(3), 
						rs.getDouble(4),
						rs.getInt(5)
				);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return priceFilter;
	}
	
	public static ProductCategory getProductCategory(int productCategoryID) {
		ProductCategory productCategory = null;
		String sql = "select * from tblProductCategory where ProductCategoryID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productCategoryID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				productCategory = new ProductCategory(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3)
				);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productCategory;
	}
	
	public static int getProductListRowCount(int productCategoryID, int brandID, 
			int priceFilterID, double minPrice, double maxPrice) {
		
		int rowCount = 0;
		String sql = "select count(*) from tblProduct where ProductCategoryID = ?";
					
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = null;
			
			if(brandID != 0 && priceFilterID != 0) {
				sql += " and BrandID = ? and Price - (Price*Discount/100) between ? and ?";
				ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setInt(2, brandID);
				ps.setDouble(3, minPrice);
				ps.setDouble(4, maxPrice);
			}	
			else
				if(brandID != 0) {
					sql += " and BrandID = ?";
					ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setInt(2, brandID);
				}
				else
					if(priceFilterID != 0) {
						sql += " and Price - (Price*Discount/100) between ? and ?";
						ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ps.setDouble(2, minPrice);
						ps.setDouble(3, maxPrice);
					}
					else
						ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						
			ps.setInt(1, productCategoryID);
						
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			rowCount = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	public static ArrayList<Product> getProductList(int productCategoryID, int brandID, int priceFilterID,
			double minPrice, double maxPrice, int sortByPrice, int currentPage){
		
		ArrayList<Product> productList = new ArrayList<Product>();
		String sql1 = "select * from tblProduct where ProductCategoryID = ?";
		String sql2 = sortByPrice == 1 ? " order by ProductID desc offset ? rows fetch next 9 rows only" : 
			" order by ProductID asc offset ? rows fetch next 9 rows only";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = null;
			
			if(brandID != 0 && priceFilterID != 0) {
				sql1 += " and BrandID = ? and Price - (Price*Discount/100) between ? and ?" + sql2;
				ps = con.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setInt(2, brandID);
				ps.setDouble(3, minPrice);
				ps.setDouble(4, maxPrice);
				ps.setInt(5, 9 * (currentPage - 1));
			}
			else 
				if(brandID != 0) {
					sql1 += " and BrandID = ?" + sql2;
					ps = con.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setInt(2, brandID);
					ps.setInt(3, 9 * (currentPage - 1));
				}
				else
					if(priceFilterID != 0) {
						sql1 += " and Price - (Price*Discount/100) between ? and ?" + sql2;
						ps = con.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ps.setDouble(2, minPrice);
						ps.setDouble(3, maxPrice);
						ps.setInt(4, 9 * (currentPage - 1));
					}
					else {
						sql1 += sql2;
						ps = con.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ps.setInt(2, 9 * (currentPage - 1));
					}
			
			ps.setInt(1, productCategoryID);
			
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
	
	public static ArrayList<Product> getHotSaleProductList(int productCategoryID){
		ArrayList<Product> hotSaleProductList = new ArrayList<Product>();
		String sql = "select top 4 * from tblProduct where ProductCategoryID = ? and Discount > 0";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productCategoryID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				hotSaleProductList.add(new Product(
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
		
		return hotSaleProductList;
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
	
	public static ArrayList<Brand> getbrandList(){
		ArrayList<Brand> brandList = new ArrayList<Brand>();
		String sql = "select * from tblBrand";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				brandList.add(new Brand(
						rs.getInt(1),
						rs.getString(2)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return brandList;
	}
	
	public static ArrayList<ProductOfBrandCount> getProductOfBrandCountList(int productCategoryID){
		ArrayList<ProductOfBrandCount> productOfBrandCountList = new ArrayList<ProductOfBrandCount>();
		String sql = "select BrandID, count(*) from tblProduct where ProductCategoryID = ? group by BrandID";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productCategoryID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				productOfBrandCountList.add(new ProductOfBrandCount(
						rs.getInt(1),
						rs.getInt(2)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productOfBrandCountList;
	}
	
	public static ArrayList<PriceFilter> getPriceFilterList(){
		ArrayList<PriceFilter> priceFilterList = new ArrayList<PriceFilter>();
		String sql = "select * from tblPriceFilter order by Orders asc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				priceFilterList.add(new PriceFilter(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getDouble(3), 
						rs.getDouble(4),
						rs.getInt(5)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return priceFilterList;
	}
	
	public static ArrayList<ProductOfProductCategoryCount> getProductOfProductCategoryCountList(){
		ArrayList<ProductOfProductCategoryCount> productOfProductCategoryCountList = new ArrayList<ProductOfProductCategoryCount>();
		String sql = "select ProductCategoryID, count(*) from tblProduct group by ProductCategoryID";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				productOfProductCategoryCountList.add(new ProductOfProductCategoryCount(
						rs.getInt(1),
						rs.getInt(2)
				));
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productOfProductCategoryCountList;
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
