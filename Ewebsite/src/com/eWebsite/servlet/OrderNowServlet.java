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
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			// out.println("this is order now and order Id: "+id);

			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {
				String ProductId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if (productQuantity <= 0) {
					productQuantity = 1;
				}

				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(ProductId));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));

				OrderDao orderDao = new OrderDao(DBcon.getConnection());
				boolean result = orderDao.insertOrder(orderModel);

				if (result) {

					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(ProductId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}

					response.sendRedirect("order.jsp");
				} else {
					out.print("order failed");
				}
			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			// System.out.println(e.printStackTrace());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
