package com.util.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/Axis_Bank?useSSL=false&serverTimezone=UTC";
	private static final String USERNAME = "root";     // MySQL username
	private static final String PASSWORD = "password"; // MySQL password

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Loading MySQL JDBC driver
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL JDBC driver not found", e);
		}
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
