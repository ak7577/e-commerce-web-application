package com.eWebsite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eWebsite.connection.DBcon;
import com.eWebsite.dao.OrderDao;
import com.eWebsite.model.Cart;
import com.eWebsite.model.Order;
import com.eWebsite.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out =response.getWriter()){
			//out.print("check out servlet");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date();
			
			//RETRIVING ALL THE CART PRODUCTS
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			//(user authentification)TO check user logged in?
			User auth = (User) request.getSession().getAttribute("auth");
			
			//check auth and cart list 
			if(cart_list != null && auth!= null) {
				for(Cart c:cart_list) {
					
					Order orderModel = new Order();
					orderModel.setId(c.getId());
					orderModel.setUid(auth.getId());
					orderModel.setQuantity(c.getQuantity());
					orderModel.setDate(formatter.format(date));
					
					//instantiate the dao class
					OrderDao oDao = new OrderDao(DBcon.getConnection());
					//calling the insert method
					boolean result = oDao.insertOrder(orderModel);
					if(!result) {
						break;
					}
				}
				
				cart_list.clear();
				response.sendRedirect("order.jsp");
			}else {
				if(auth==null)response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
			
			
		}catch(Exception e) {
			System.out.print(e);
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
