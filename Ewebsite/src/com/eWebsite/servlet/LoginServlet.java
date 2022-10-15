package com.eWebsite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.User;

import com.eWebsite.connection.DBcon;
import com.eWebsite.dao.UserDao;
import com.eWebsite.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out=response.getWriter()){
			//out.print("this is login servlet"); //*de-bug to check servlet connection
		
			//now to grab user data from the login form
			String email= request.getParameter("login-email");
			String password= request.getParameter("login-password");
			
			//and print the data grabed
		out.print(email+password); // to check we are getting the data from form
			
			//user auth 
			
			try {
				UserDao udao= new UserDao(DBcon.getConnection());
				User user= udao.userLogin(email, password);
			
				if(user!=null) {
					//out.print("user login sucess");
					request.getSession().setAttribute("auth", user);// saving the info in session
			
				//for admin **************
					if(email.equals("admin@gmail.com") && password.equals("admin")) {
						//out.print("user login sucess");
						
						response.sendRedirect("admin/addNewProduct.jsp");
					}else
					
				//for admin auth ends here************
					
					//request.getSession().setAttribute("auth", user);// saving the info in session
					response.sendRedirect("index.jsp");
				}
				else {
					//out.print("user login failed");
					response.sendRedirect("login.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
	}

}
