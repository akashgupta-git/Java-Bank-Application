package com.dal;

import com.util.dbutil.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		String email = (session != null) ? (String) session.getAttribute("email") : null;
		Integer customerId = (session != null) ? (Integer) session.getAttribute("customerId") : null;

		if (email == null || customerId == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		double amount = Double.parseDouble(request.getParameter("amount"));

		out.println("<!DOCTYPE html><html><head>");
		out.println("<title>Withdrawal Result</title>");
		out.println(
				"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("</head><body class='bg-light d-flex align-items-center justify-content-center vh-100'>");
		out.println("<div class='card p-4 shadow' style='min-width: 350px;'>");

		try (Connection conn = DBUtil.getConnection()) {
			String selectSql = "SELECT balance FROM customers WHERE email=?";
			PreparedStatement selectStmt = conn.prepareStatement(selectSql);
			selectStmt.setString(1, email);
			ResultSet rs = selectStmt.executeQuery();

			if (rs.next()) {
				double currentBalance = rs.getDouble("balance");

				if (amount > currentBalance) {
					out.println("<h3 class='text-warning text-center'>⚠️ Insufficient Balance</h3>");
					out.println("<p class='text-center'>Your current balance is ₹" + currentBalance + "</p>");
				} else {
					String updateSql = "UPDATE customers SET balance = balance - ? WHERE email = ?";
					PreparedStatement updateStmt = conn.prepareStatement(updateSql);
					updateStmt.setDouble(1, amount);
					updateStmt.setString(2, email);
					int rows = updateStmt.executeUpdate();

					if (rows > 0) {
						// Insert into transactions table
						String txnSql = "INSERT INTO transactions (customer_id, type, amount) VALUES (?, ?, ?)";
						PreparedStatement txnStmt = conn.prepareStatement(txnSql);
						txnStmt.setInt(1, customerId);
						txnStmt.setString(2, "WITHDRAW");
						txnStmt.setDouble(3, amount);
						txnStmt.executeUpdate();

						out.println("<h3 class='text-success text-center'>✅ Withdrawal Successful!</h3>");
						out.println("<p class='text-center'>₹" + amount + " debited from your account.</p>");
					} else {
						out.println("<h3 class='text-danger text-center'>❌ Withdrawal Failed!</h3>");
					}
				}
			} else {
				out.println("<h3 class='text-danger text-center'>❌ Account not found.</h3>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h3 class='text-danger text-center'>❌ Error occurred during withdrawal.</h3>");
		}

		out.println("<div class='text-center mt-3'>");
		out.println("<a href='dashboard.jsp' class='btn btn-outline-primary w-100'>⬅ Back to Dashboard</a>");
		out.println("</div></div></body></html>");
	}
}
