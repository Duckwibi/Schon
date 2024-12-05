package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JoinTable.CartJoinProduct;
import Model.Cart;
import Model.Product;

public class UpdateCartDAO {
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
	
	public static boolean checkProductID(int productID) {
		boolean check = true;
		String sql = "select count(*) from tblProduct where ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			check = rs.getInt(1) == 0 ? false : true;
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static CartJoinProduct getCartJoinProduct(int customerID, int productID) {
		CartJoinProduct cartJoinProduct = null;
		String sql = "select * from tblCart C join tblProduct P";
		sql += " on C.ProductID = P.ProductID where C.CustomerID = ? and C.ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ps.setInt(2, productID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())	
				cartJoinProduct = new CartJoinProduct(
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
				);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cartJoinProduct;
	}
	
	public static void updateCart(Cart cart) {
		String sql = "update tblCart set Quantity = ? where CustomerID = ? and ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getQuantity());
			ps.setInt(2, cart.getCustomerID());
			ps.setInt(3, cart.getProductID());
			
			ps.executeUpdate();
			
			con.close(); ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
