package com.eWebsite.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {
	private static Connection connection=null;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_cart","root","");
			//DriverManager.getConnection(url, userName, password);
			System.out.print("connected"); // shows in console
		}
		return connection;
	}
}
