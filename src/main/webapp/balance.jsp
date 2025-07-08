<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
Double balance = (Double) request.getAttribute("balance");

if (username == null || balance == null) {
	response.sendRedirect("login.html");
	return;
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Balance - Axis Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body
	class="bg-light d-flex align-items-center justify-content-center vh-100">

	<div class="card p-4 shadow text-center"
		style="min-width: 350px; max-width: 500px;">
		<h3 class="mb-3 text-primary">📊 Your Account Balance</h3>
		<h5 class="mb-4 text-muted">
			Hello,
			<%=username%>
			👋
		</h5>

		<h2 class="text-success fw-bold">
			₹
			<%=String.format("%.2f", balance)%></h2>

		<hr class="my-4" />

		<a href="dashboard.jsp" class="btn btn-outline-secondary w-100">⬅
			Back to Dashboard</a>
	</div>

</body>
</html>
