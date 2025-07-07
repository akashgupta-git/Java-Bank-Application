package com.dal;

import com.pojo.Customer;
import com.util.dbutil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAL {

	// Insert new customer
	public boolean insertCustomer(Customer cust) {
		String sql = "INSERT INTO customers (name, email, password) VALUES (?, ?, ?)";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cust.getName());
			stmt.setString(2, cust.getEmail());
			stmt.setString(3, cust.getPassword());

			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Retrieve all customers
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
