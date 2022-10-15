package com.eWebsite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eWebsite.connection.DBcon;
import com.eWebsite.dao.AddProductDao;
import com.eWebsite.model.Product;
import com.eWebsite.model.User;

/**
 * Servlet implementation class AddNewProductServlet
 */
@WebServlet("/add-product")
public class AddNewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter out = response.getWriter()) {

			User auth = (User) request.getSession().getAttribute("auth");
			
			
			if (auth != null) {
				String name = request.getParameter("productName");
				String category = request.getParameter("productCategory");
				String price = request.getParameter("productPrice");
			//	String image = request.getParameter("productImage");
				out.print(name);
				
				Product orderModel = new Product();
				orderModel.setName(name);
				orderModel.setCategory(category);
				orderModel.setPrice(Double.parseDouble(price));
				//orderModel.setImage();
				AddProductDao productDao = new AddProductDao(DBcon.getConnection());
				boolean result = productDao.addProducts(orderModel);
}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
