package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Contact;

public class CreatedContactDAO {
	public static int getMaxContactID() {
		int maxContactID = 0;
		String sql = "select case when max(ContactID) is null then 0 else max(ContactID) end from tblContact";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			maxContactID = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxContactID;
	}
	
	public static void createContact(Contact contact) {
		String sql = "insert into tblContact values (?,?,?,?,?)";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, contact.getContactID());
			ps.setTimestamp(2, contact.getCreatedDate());
			ps.setString(3, contact.getName());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getMessage());
			
			ps.executeUpdate();
			
			con.close(); ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
