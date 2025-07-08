package com.dal;

import com.util.dbutil.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/balance")
public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");

		if (email == null || email.trim().isEmpty()) {
			response.sendRedirect("login");
			return;
		}

		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT balance FROM customers WHERE email=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email.trim());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				double balance = rs.getDouble("balance");
				request.setAttribute("balance", balance);
				request.getRequestDispatcher("balance.jsp").forward(request, response);
			} else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<h3 style='color:red;'>Account not found.</h3>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h3 style='color:red;'>Error retrieving balance.</h3>");
		}
	}
}
