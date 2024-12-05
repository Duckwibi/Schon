package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Customer;

public class RegisterDAO {
	public static boolean checkUserName(String userName) {
		boolean check = true;
		String sql = "select count(*) from tblCustomer where UserName = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			check = rs.getInt(1) == 0 ? true : false;
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static int getMaxCustomerID() {
		int maxCustomerID = 0;
		String sql = "select top 1 CustomerID from tblCustomer order by CustomerID desc";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				maxCustomerID = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxCustomerID;
	}
	
	public static void createCustomer(Customer customer) {
		String sql = "insert into tblCustomer values(?,?,?,?,?,?,?,?)";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, customer.getCustomerID());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getPhone());
			ps.setString(6, customer.getAddress());
			ps.setString(7, customer.getUserName());
			ps.setString(8, customer.getPassword());
			
			ps.executeUpdate();
			
			con.close(); ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
