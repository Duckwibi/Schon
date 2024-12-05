package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.DiscountCode;
import Model.ShippingPrice;

public class UpdateTotalDAO {
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
	
}
