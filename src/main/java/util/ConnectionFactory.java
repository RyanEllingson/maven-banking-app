package util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection conn;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				URI dbUri = new URI(System.getenv("DATABASE_URL"));
				
				String username = dbUri.getUserInfo().split(":")[0];
				String password = dbUri.getUserInfo().split(":")[1];
				String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
				
				conn = DriverManager.getConnection(dbUrl, username, password);
			} catch (URISyntaxException | SQLException e) {
				e.printStackTrace();
			}
			
		}
		return conn;
	}
}
