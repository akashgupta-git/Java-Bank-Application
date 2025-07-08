package com.dal;

import com.util.dbutil.DBUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/chklogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT * FROM customers WHERE email=? AND password=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("customerId", rs.getInt("id"));
				session.setAttribute("username", rs.getString("name"));
				session.setAttribute("email", rs.getString("email"));

				response.sendRedirect("dashboard.jsp");

			} else {
				out.println("<!DOCTYPE html>");
				out.println("<html><head><title>Login Failed</title>");
				out.println(
						"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
				out.println("</head><body class='bg-light d-flex align-items-center justify-content-center vh-100'>");
				out.println("<div class='card p-4 shadow text-center' style='min-width: 350px;'>");
				out.println("<h3 class='mb-3 text-danger'>Invalid Credentials!</h3>");
				out.println("<a href='login.jsp' class='btn btn-primary'>Try Again</a>");
				out.println("</div></body></html>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.println("<!DOCTYPE html>");
			out.println("<html><head><title>Server Error</title>");
			out.println(
					"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
			out.println("</head><body class='bg-light d-flex align-items-center justify-content-center vh-100'>");
			out.println("<div class='card p-4 shadow text-center' style='min-width: 350px;'>");
			out.println("<h3 class='mb-3 text-danger'>Server Error Occurred!</h3>");
			out.println("<a href='login.jsp' class='btn btn-secondary'>Back to Login</a>");
			out.println("</div></body></html>");
		}
	}
}
