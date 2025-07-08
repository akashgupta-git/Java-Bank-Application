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
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Dashboard - Axis Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body class="bg-light">

	<div class="container py-5">
		<div class="text-center mb-4">
			<h2 class="text-primary">
				Welcome to Axis Bank,
				<%=username%>
				👋
			</h2>
			<p class="lead">Choose an action below:</p>
		</div>

		<div class="row g-4 justify-content-center">
			<div class="col-md-3">
				<a href="deposit.html" class="btn btn-success btn-lg w-100">💰
					Deposit</a>
			</div>
			<div class="col-md-3">
				<a href="withdraw.html" class="btn btn-warning btn-lg w-100">🏧
					Withdraw</a>
			</div>
			<div class="col-md-3">
				<a href="balance" class="btn btn-info btn-lg w-100">📊
					Check Balance</a>
			</div>
			<div class="col-md-3">
				<a href="transactions" class="btn btn-secondary btn-lg w-100">📜
					Transactions</a>
			</div>
			<div class="col-md-3">
				<a href="logout" class="btn btn-danger btn-lg w-100">🚪 Logout</a>
			</div>
		</div>
	</div>

</body>
</html>
