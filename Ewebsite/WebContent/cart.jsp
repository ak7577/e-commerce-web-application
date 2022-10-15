<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.eWebsite.connection.DBcon"%>
<%@page import="com.eWebsite.dao.*"%>
<%@page import="com.eWebsite.model.*"%>
<%@page import="com.eWebsite.servlet.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
//for setting 2 degits in decimal 
	DecimalFormat dcf= new DecimalFormat("#.##");
	request.setAttribute("dcf",dcf);

	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("auth", auth);
	}

	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	List<Cart> cartProduct = null;
	if (cart_list != null) {
		ProductDao pDao = new ProductDao(DBcon.getConnection());
		cartProduct = pDao.getCartProducts(cart_list);
		request.setAttribute("cart_list", cart_list);
		//below code is for the total price section 
		double total = pDao.getTotalCartPrice(cart_list);
		request.setAttribute("total", total);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<%@ include file="includes/head.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size
	=25px;
}
</style>

</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price: ${(total>0)?dcf.format(total):0} Rs</h3>
			<a class="mx-3 btn btn-primary" href="cart-check-out">check out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (cart_list != null) {
						for (Cart c : cartProduct) {
				%>

				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=dcf.format(c.getPrice())%></td>
					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre"
									href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control" value=<%=c.getQuantity()%>
									readonly> <a class="btn btn-sm btn-incre"
									href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">BUY</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%= c.getId() %>">Remove</a></td>
				</tr>


				<%
					}
					}
				%>

			</tbody>
		</table>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>