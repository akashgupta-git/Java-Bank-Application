package com.dal;

import com.util.dbutil.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
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
			response.sendRedirect("login");
			return;
		}

		double amount = Double.parseDouble(request.getParameter("amount"));

		out.println("<!DOCTYPE html><html><head>");
		out.println("<title>Deposit Result</title>");
		out.println(
				"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'/>");
		out.println("</head><body class='bg-light d-flex justify-content-center align-items-center vh-100'>");
		out.println("<div class='card p-4 shadow' style='min-width:350px;'>");

		try (Connection conn = DBUtil.getConnection()) {

			// 1. Update balance
			String updateSql = "UPDATE customers SET balance = balance + ? WHERE email = ?";
			PreparedStatement updateStmt = conn.prepareStatement(updateSql);
			updateStmt.setDouble(1, amount);
			updateStmt.setString(2, email);
			int rows = updateStmt.executeUpdate();

			if (rows > 0) {
				// 2. Insert into transactions table
				String txnSql = "INSERT INTO transactions (customer_id, type, amount) VALUES (?, ?, ?)";
				PreparedStatement txnStmt = conn.prepareStatement(txnSql);
				txnStmt.setInt(1, customerId);
				txnStmt.setString(2, "DEPOSIT");
				txnStmt.setDouble(3, amount);
				txnStmt.executeUpdate();

				out.println("<h3 class='text-success text-center'>✅ Deposit Successful!</h3>");
				out.println("<p class='text-center'>₹" + amount + " added to your account.</p>");
			} else {
				out.println("<h4 class='text-danger text-center'>❌ Deposit Failed.</h4>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h4 class='text-danger text-center'>⚠️ Error occurred during deposit.</h4>");
		}

		out.println("<div class='text-center mt-3'>");
		out.println("<a href='dashboard.jsp' class='btn btn-outline-primary w-100'>⬅ Back to Dashboard</a>");
		out.println("</div></div></body></html>");
	}
}
