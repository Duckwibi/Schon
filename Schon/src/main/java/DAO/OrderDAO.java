package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JoinTable.CartJoinProduct;
import Model.Cart;
import Model.DiscountCode;
import Model.Order;
import Model.Product;
import Model.ShippingPrice;

public class OrderDAO {
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
	
	public static int getMaxOrderID() {
		int maxOrderID = 0;
		String sql = "select case when max(OrderID) is null then 0 else max(OrderID) end from tblOrder";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			maxOrderID = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxOrderID;
	}
	
	public static void createOrderAndOrderDetail(Order order, ArrayList<CartJoinProduct> cartJoinProductList) {
		String sql = "insert into tblOrder values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, order.getOrderID());
			ps.setTimestamp(2, order.getCreatedDate());
			ps.setString(3, order.getFirstName());
			ps.setString(4, order.getLastName());
			ps.setString(5, order.getPhone());
			ps.setString(6, order.getEmail());
			ps.setString(7, order.getAddress());
			ps.setString(8, order.getNote());
			ps.setInt(9, order.getDiscount());
			ps.setDouble(10, order.getShipping());
			ps.setInt(11, order.getCustomerID());
			
			ps.executeUpdate();
			
			ps.close();
			
			sql = "insert into tblOrderDetail select ?, C.ProductID, C.Quantity, P.Discount, P.Price from";
			sql	+= " tblCart C join tblProduct P on C.ProductID = P.ProductID where C.CustomerID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, order.getOrderID());
			ps.setInt(2, order.getCustomerID());
			
			ps.executeUpdate();
			
			ps.close();
			
			sql = "update tblProduct set Quantity = ? where ProductID = ?";
			for(CartJoinProduct item : cartJoinProductList) {
				ps = con.prepareStatement(sql);
				ps.setInt(1, item.getProduct().getQuantity() - item.getCart().getQuantity());
				ps.setInt(2, item.getCart().getProductID());
				
				ps.executeUpdate();
				
				ps.close();
			}
				
			sql = "delete from tblCart where CustomerID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, order.getCustomerID());
			
			ps.executeUpdate();
				
			con.close(); ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
