package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	public static Connection getConnection() {
		
		Connection con = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Schon;encrypt=true;trustServerCertificate=true","sa","1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		return con;
	}
}
