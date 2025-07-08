<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("login.html");
	return;
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Deposit - Axis Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body
	class="bg-light d-flex justify-content-center align-items-center vh-100">

	<div class="card shadow p-4"
		style="min-width: 350px; max-width: 500px;">
		<h3 class="mb-4 text-center">Deposit Money</h3>

		<form method="POST" action="deposit">
			<div class="mb-3">
				<label for="amount" class="form-label">Enter Amount (₹)</label> <input
					type="number" class="form-control" id="amount" name="amount"
					min="1" required>
			</div>
			<button type="submit" class="btn btn-success w-100">Deposit</button>
		</form>

		<div class="mt-4 text-center">
			<a href="dashboard.jsp" class="btn btn-outline-secondary w-100">⬅️
				Back to Dashboard</a>
		</div>
	</div>

</body>
</html>
