<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login - Axis Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body
	class="bg-light d-flex align-items-center justify-content-center vh-100">

	<div class="card shadow p-4"
		style="min-width: 350px; max-width: 400px;">
		<h3 class="mb-4 text-center">Login</h3>

		<% String error = request.getParameter("error"); if
		("1".equals(error)) { %>
		<div class="alert alert-danger text-center" role="alert">
			Invalid email or password!</div>
		<% } %>


		<form method="POST" action="chklogin">
			<div class="mb-3">
				<label for="email" class="form-label">Email address</label> <input
					type="email" class="form-control" id="email" name="email"
					placeholder="Enter email" required />
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password" name="password"
					placeholder="Enter password" required />
			</div>

			<button type="submit" class="btn btn-primary w-100">Login</button>
		</form>

		<hr class="my-4" />

		<div class="text-center">
			<p class="mb-2">New user?</p>
			<a href="register.html" class="btn btn-outline-secondary w-100">Create
				an Account</a>
		</div>
	</div>

</body>
</html>
