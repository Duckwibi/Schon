package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JoinTable.ReviewJoinCustomer;
import Model.Customer;
import Model.Review;

public class CreateReviewDAO {
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
	
	public static boolean checkReview(int customerID, int productID) {
		boolean check = true;
		String sql = "select count(*) from tblReview where CustomerID = ? and ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ps.setInt(2, productID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			check = rs.getInt(1) == 0 ? false : true;
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static void createReview(Review review) {
		String sql = "insert into tblReview values(?,?,?,?,?)";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, review.getCustomerID());
			ps.setInt(2, review.getProductID());
			ps.setInt(3, review.getVote());
			ps.setTimestamp(4, review.getCreatedDate());
			ps.setString(5, review.getMessage());
			
			ps.executeUpdate();
			
			con.close(); ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ReviewJoinCustomer getReviewJoinCustomer(int customerID, int productID) {
		ReviewJoinCustomer reviewJoinCustomer = null;
		String sql = "select * from tblReview R join tblCustomer C on R.CustomerID = C.CustomerID";
		sql += " where R.CustomerID = ? and R.ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			ps.setInt(2, productID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				reviewJoinCustomer = new ReviewJoinCustomer(
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
				);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reviewJoinCustomer;
	}
	
	public static int getVoteAVG(int productID) {
		int voteAVG = 0;
		String sql = "select cast(round(avg(1.0 * Vote), 0) as int) from tblReview where ProductID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			voteAVG = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return voteAVG;
	}
}
