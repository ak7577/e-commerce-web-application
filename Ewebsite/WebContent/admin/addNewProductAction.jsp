<%@page import="com.eWebsite.connection.DBcon"%> 
<%@page import="java.sql.*"%>
<%@page import="java.io.FileNotFoundException" %>
<%@page import= "java.io.FileOutputStream"%>
<%@page import= "java.io.IOException"%>
<%@page import= "java.io.InputStream"%>
<%@page import= "java.io.FileInputStream"%>
<%@page import= "java.io.IOException"%>
<%@page import= "java.nio.file.Files"%>
<%@page import= "java.nio.file.Paths"%>
 <%@page import="javax.servlet.annotation.MultipartConfig"%>
  
<%
String id=request.getParameter("id");
String name=request.getParameter("productName");
String category=request.getParameter("productCategory");
String price=request.getParameter("productPrice");
String image=request.getParameter("productImage");

try{
	
	Connection con=DBcon.getConnection();
	PreparedStatement ps=con.prepareStatement("INSERT INTO `products` VALUES (?,?,?,?,?)");
	ps.setString(1,id);
	ps.setString(2,name);
	ps.setString(3,category);
	ps.setString(4,price);
	ps.setString(5,image);
	  
	ps.executeUpdate();
	response.sendRedirect("addNewProduct.jsp?msg=done");
	
}catch(Exception e){
	response.sendRedirect("addNewProduct.jsp?msg=error");
	System.out.println(e);
}

%>