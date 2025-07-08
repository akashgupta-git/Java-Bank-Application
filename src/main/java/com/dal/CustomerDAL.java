package com.dal;

import com.pojo.Customer;
import com.util.dbutil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Layer for Customer operations.
 */
public class CustomerDAL {

	/**
	 * Inserts a new customer into the database.
	 *
	 * @param cust the customer object
	 * @return true if inserted successfully, false otherwise
	 */
	public boolean insertCustomer(Customer cust) {
		String sql = "INSERT INTO customers (name, email, password) VALUES (?, ?, ?)";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cust.getName());
			stmt.setString(2, cust.getEmail());
			stmt.setString(3, cust.getPassword());

			int rows = stmt.executeUpdate();
			return rows > 0;

		} catch (SQLException e) {
			System.err.println("Error inserting customer: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Retrieves all customers from the database.
	 *
	 * @return list of customers
	 */
	public List<Customer> getAllCustomers() {
		List<Customer> list = new ArrayList<>();
		String sql = "SELECT * FROM customers";

		try (Connection conn = DBUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Customer cust = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"));
				list.add(cust);
			}

		} catch (SQLException e) {
			System.err.println("Error fetching customers: " + e.getMessage());
		}

		return list;
	}

	/**
	 * Checks if a customer exists by email.
	 *
	 * @param email customer email
	 * @return true if customer exists, false otherwise
	 */
	public boolean customerExists(String email) {
		String sql = "SELECT id FROM customers WHERE email = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			System.err.println("Error checking customer existence: " + e.getMessage());
		}

		return false;
	}
}
