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
<meta charset="ISO-8859-1">
<%@ include file="adminHeader.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="adminNav.jsp"%>

	<div class="container" >
	
<form action="addNewProductAction.jsp" method="post" class="row g-3">
  <div class="col-md-6">
    <label for="inputEmail4" class="form-label">Product Name</label>
    <input type="text" name="productName" class="form-control" placeholder="Product Name" aria-label="Product Name">
  </div>
  
  <div class="col-md-6">
    <label for="inputPassword4" class="form-label">Product Category</label>
   <input type="text" name="productCategory" class="form-control" placeholder="Product Category" aria-label="Product Category">
  </div>

 
  <div class="col-md-6">
    <label for="inputCity" class="form-label">Product Price</label>
   <input type="text" name="productPrice" class="form-control" placeholder="Product Price" aria-label="Product Price">
  </div>
  
  
 <div class="col-md-12">
  <label for="formFile" class="form-label">Product Image</label>
  <input name="productImage" class="form-control" type="file" id="formFile">
<hr>
</div>

  <div class="col-12">
    <button type="submit" class="btn btn-primary">Submit</button>
  </div>
</form>
</div>

</body>
</html>