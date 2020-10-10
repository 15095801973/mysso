package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="*.do")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlString = request.getRequestURI();
		if(urlString.endsWith("view.do")){
			request.getRequestDispatcher("WEB-INF/jsp/view.jsp").forward(request, response);
		}
		else if(urlString.endsWith("logout.do")) {
			response.getWriter().write("logouted");
		}
	}

}
