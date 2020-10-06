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

@WebServlet(value="/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CAS_ST=request.getParameter("CAS_ST");
		String host=request.getParameter("host");
		String app=request.getParameter("app");
		String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
		String sessionId=request.getParameter("sessionId");
		HttpSession session=request.getSession();
		session.setAttribute("LOCAL_SERVICE", LOCAL_SERVICE);
		//response.getWriter().println("login.doGet...");

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		//response.sendRedirect("reDirect");
		//response.getWriter().println("getUser.doGet...");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id=request.getParameter("id");	
	String pwd=request.getParameter("pwd");	
	HttpSession session = request.getSession();
	String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");	

	System.out.println("Local_serve:"+LOCAL_SERVICE);
	session.setAttribute("id", id);
	session.setAttribute("pwd", pwd);
	User user =DB.findUser(id,pwd);
	if (user != null) {
		System.out.println("密码正确");
		session.setAttribute("user", user);
		String CAS_ST = user.getId() + System.nanoTime();
		// 为简化设计TGS=ST
		Cookie cookie = new Cookie(Constants.CAS_TGS, CAS_ST);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		DB.addServiceTicket(user,CAS_ST);
		if (LOCAL_SERVICE != null && !LOCAL_SERVICE.equals("")) {
			response.sendRedirect(LOCAL_SERVICE + "?"
					+ Constants.CAS_ST + "=" + CAS_ST + "&"
					+ Constants.LOCAL_SERVICE + "=" + LOCAL_SERVICE);
		} else
			response.sendRedirect(request.getContextPath()+"/main.do");
	} else {
		System.out.println("密码错误");
		session.setAttribute("msg", "login failed");
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	//request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
	}
	

}