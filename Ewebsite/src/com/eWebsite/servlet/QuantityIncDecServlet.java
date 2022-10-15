package com.eWebsite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eWebsite.model.Cart;

/**
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//PrintWriter out=response.getWriter();
			//out.println("inc-dec servlet"); to check if its redirecting
			
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter();){
			String action=request.getParameter("action");
			int id =Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Cart>	cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			if(action !=null&& id>1) 
			{
				//for increment 
				if(action.equals("inc")) 
				{
					for(Cart c:cart_list) 
					{
						if(c.getId()==id) {
							int quantity=c.getQuantity();
							quantity++;
							c.setQuantiy(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				//for decrement 
				if(action.equals("dec")) 
				{
					for(Cart c:cart_list) 
					{
						if(c.getId()==id && c.getQuantity()>1) {
							int quantity=c.getQuantity();
							quantity--;
							c.setQuantiy(quantity);
							break;
							
						}
					}
					response.sendRedirect("cart.jsp");
				}
			}else {
					response.sendRedirect("cart.jsp");

				}
		}
	}

}
