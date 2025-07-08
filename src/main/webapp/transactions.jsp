<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pojo.Transaction"%>
<%@ page session="true"%>
<%
String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("login.jsp");
	return;
}

List<Transaction> txList = (List<Transaction>) request.getAttribute("txList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Transaction History</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<h2 class="text-primary text-center mb-4">📜 Transaction History</h2>

		<div class="card shadow">
			<div class="card-body">
				<%
				if (txList != null && !txList.isEmpty()) {
				%>
				<table class="table table-bordered table-striped text-center">
					<thead class="table-dark">
						<tr>
							<th>#</th>
							<th>Type</th>
							<th>Amount</th>
							<th>Date & Time</th>
						</tr>
					</thead>
					<tbody>
						<%
						int count = 1;
						for (Transaction tx : txList) {
						%>
						<tr>
							<td><%=count++%></td>
							<td><%=tx.getType()%></td>
							<td>&#8377;<%=tx.getAmount()%></td>
							<!-- ₹ symbol -->
							<td><%=tx.getTimestamp()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<%
				} else {
				%>
				<p class="text-center text-muted">No transactions found.</p>
				<%
				}
				%>
			</div>
		</div>

		<div class="text-center mt-4">
			<a href="dashboard.jsp" class="btn btn-outline-primary">⬅ Back to
				Dashboard</a>
		</div>
	</div>
</body>
</html>
