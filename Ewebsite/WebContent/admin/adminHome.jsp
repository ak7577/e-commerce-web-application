<%@page import="com.eWebsite.connection.DBcon"%>
<%@page import="com.eWebsite.model.*"%>
<%@page import="com.eWebsite.dao.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("auth", auth);
	} else {
		response.sendRedirect("../login.jsp");
	}

	AddProductDao pd = new AddProductDao(DBcon.getConnection());
	//List<Product> products = pd.addProducts();

	//to get cart size number on the navbar cart

	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="adminHeader.jsp"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="adminNav.jsp"%>

	<div class="container">

		<div class="row row-cols-1 row-cols-md-3 g-4">
			<div class="col">
				<div class="card h-100">
					<img src="..." class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><center>USERS</center> </h5>
						<p class="card-text">Total number of users 99</p>
					</div>
				</div>
			</div>
			
			<div class="col">
				<div class="card h-100">
					<img src="..." class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><center>PRODUCTS</center> </h5>
						<p class="card-text">Total number of products 99</p>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>