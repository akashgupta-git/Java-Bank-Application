package com.util.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/Axis_Bank"; // change db name if needed
	private static final String USERNAME = "root"; // your MySQL username
	private static final String PASSWORD = "password"; // your MySQL password

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
