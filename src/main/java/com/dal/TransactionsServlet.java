package com.dal;

import com.pojo.Transaction;
import com.util.dbutil.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Integer customerId = (Integer) session.getAttribute("customerId");

		if (customerId == null) {
			response.sendRedirect("login");
			return;
		}

		List<Transaction> transactionList = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT id, customer_id, type, amount, timestamp FROM transactions WHERE customer_id=? ORDER BY timestamp DESC";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Transaction txn = new Transaction(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("type"),
						rs.getInt("amount"), rs.getTimestamp("timestamp"));
				transactionList.add(txn);
			}

			request.setAttribute("txList", transactionList);
			request.getRequestDispatcher("transactions.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("<h3 style='color:red;'>Error fetching transaction history.</h3>");
		}
	}
}
