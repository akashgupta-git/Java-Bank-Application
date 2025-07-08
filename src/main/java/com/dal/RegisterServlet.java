package com.dal;

import com.pojo.Customer;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirmPassword");

		if (!password.equals(confirm)) {
			out.println("<h3 style='color:red;'>Passwords do not match!</h3>");
			out.println("<a href='register.html'>Try Again</a>");
			return;
		}

		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPassword(password);

		CustomerDAL dal = new CustomerDAL();

		// Prevent duplicate email registration
		if (dal.customerExists(email)) {
			out.println("<h3 style='color:red;'>Email already registered!</h3>");
			out.println("<a href='register.html'>Try Again</a>");
			return;
		}

		boolean success = dal.insertCustomer(customer);

		if (success) {
			response.sendRedirect("login.jsp");
		} else {
			out.println("<h3 style='color:red;'>Registration failed. Try again.</h3>");
			out.println("<a href='register.html'>Back to Register</a>");
		}
	}
}
