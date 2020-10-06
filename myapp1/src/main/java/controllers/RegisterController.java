package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cas.server.Constants;
import database.DB;
import domains.User;

@WebServlet(value="/register.do")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		//response.getWriter().println("login.doGet...");

		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		//response.sendRedirect("reDirect");
		//response.getWriter().println("getUser.doGet...");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id=request.getParameter("id");	
	String pwd=request.getParameter("pwd");	
	HttpSession session = request.getSession();
	String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");	
	session.setAttribute("id", id);
	session.setAttribute("pwd", pwd);
	User user =DB.findUser(id,pwd);
	if (user == null) {
		DB.addUser(id, pwd);
		response.sendRedirect(request.getContextPath()+"/login.do");
	
	} else {
		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}
	}
	

}