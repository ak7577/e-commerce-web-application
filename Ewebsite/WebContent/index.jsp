<%@page import="com.eWebsite.connection.DBcon"%>
<%@page import="com.eWebsite.model.*"%>
<%@page import="com.eWebsite.dao.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("auth", auth);
	}

	ProductDao pd = new ProductDao(DBcon.getConnection());
	List<Product> products = pd.getAllProducts();

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
<title></title>
<%@ include file="includes/head.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<%
		//out.print(DBcon.getConnection());
	%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
				if (!products.isEmpty()) {
					for (Product p : products) {
						//out.println(p.getCategory());// to check if can fetch from db table prodcts
			%>

			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" style="height: 180px;"
						src="product-images/<%=p.getImage()%>" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">
							Price:
							<%=p.getPrice()%>
							Rs
						</h6>
						<h6 class="category">
							Categorey:
							<%=p.getCategory()%></h6>
						<!-- link to browse -->
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark">Add
								to cart</a> <a href="order-now?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
						</div>
					</div>
				</div>
			</div>

			<%
				}
				}
			%>

		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>