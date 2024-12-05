package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Comment;

public class CreateCommentDAO {
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
	
	public static boolean checkBlogID(int blogID) {
		boolean check = true;
		String sql = "select count(*) from tblBlog where BlogID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, blogID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			check = rs.getInt(1) == 0 ? false : true;
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static int getMaxCommentID() {
		int maxCommentID = 0;
		String sql = "select case when max(CommentID) is null then 0 else max(CommentID) end from tblComment";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			maxCommentID = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxCommentID;
	}
	
	public static void createComment(Comment comment) {
		String sql = "insert into tblComment values (?,?,?,?,?)";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, comment.getCommentID());
			ps.setTimestamp(2, comment.getCreatedDate());
			ps.setString(3, comment.getMessage());
			ps.setInt(4, comment.getBlogID());
			ps.setInt(5, comment.getCustomerID());
			
			ps.executeUpdate();
			
			con.close(); ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getCommentCount(int blogID) {
		int count = 0;
		String sql = "select count(*) from tblComment where BlogID = ?";
		
		try {
			Connection con = DBContext.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, blogID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
			con.close(); ps.close(); rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
