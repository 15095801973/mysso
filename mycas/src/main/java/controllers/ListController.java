package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cas.server.Constants;
import database.DB;
import domains.User;

@WebServlet(value="/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		//response.getWriter().println("login.doGet...");
		HttpSession Session = request.getSession();
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
		//response.sendRedirect("reDirect");
		//response.getWriter().println("getUser.doGet...");
	}
	

}