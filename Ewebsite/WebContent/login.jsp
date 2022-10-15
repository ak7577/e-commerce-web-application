<%@page import="com.eWebsite.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<% 
	User auth= (User) request.getSession().getAttribute("auth"); 
		if (auth!= null){
			request.setAttribute("auth", auth);
			response.sendRedirect("index.jsp");			
		}
		
		//to get cart size number on the navbar cart

		ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
		
		if (cart_list != null) {
			request.setAttribute("cart_list", cart_list);
		}
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login page</title>
<!-- css stars here -->
<%@ include file="includes/head.jsp"%>
</head>
<body>
<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							type="email" class="form-control" name="login-email" id="exampleInputEmail1"
							aria-describedby="emailHelp" placeholder="Enter email"> <small
							id="emailHelp" class="form-text text-muted">We'll never
							share your email with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" name="login-password" id="exampleInputPassword1"
							placeholder="Password">
					</div>
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1">
						<label class="form-check-label" for="exampleCheck1">Check
							me out</label>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
	<!-- footer stars here -->
	<%@ include file="includes/footer.jsp"%>
</body>
</html>