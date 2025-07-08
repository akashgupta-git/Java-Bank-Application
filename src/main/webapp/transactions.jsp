<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pojo.Transaction"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page session="true"%>
<%
String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("login.jsp");
	return;
}

String filter = request.getParameter("filter");
if (filter == null)
	filter = "all";

List<Transaction> fullList = (List<Transaction>) request.getAttribute("txList");
List<Transaction> txList = new ArrayList<>();

for (Transaction tx : fullList) {
	if ("all".equals(filter) || tx.getType().equalsIgnoreCase(filter)) {
		txList.add(tx);
	}
}

NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
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

		<!-- Filter -->
		<form method="get" class="mb-4 text-center">
			<label for="filter" class="me-2 fw-semibold">Filter:</label> <select
				name="filter" id="filter" onchange="this.form.submit()"
				class="form-select w-auto d-inline-block">
				<option value="all" <%="all".equals(filter) ? "selected" : ""%>>All</option>
				<option value="deposit"
					<%="deposit".equals(filter) ? "selected" : ""%>>Deposit</option>
				<option value="withdraw"
					<%="withdraw".equals(filter) ? "selected" : ""%>>Withdraw</option>
			</select>
		</form>

		<div class="card shadow">
			<div class="card-body">
				<%
				if (!txList.isEmpty()) {
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
							<td><%=tx.getType().toUpperCase()%></td>
							<td><%=currencyFormat.format(tx.getAmount())%></td>
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
				<p class="text-center text-muted">No transactions found for this
					filter.</p>
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
