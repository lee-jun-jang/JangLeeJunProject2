package controller.dataroom;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Dataroom/Logout.ict")
public class LogoutController extends HttpServlet {

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, java.io.IOException {
	
		 HttpSession session = request.getSession();
	        if (session != null) {
	        	session.removeAttribute("username");
	            session.invalidate();
	        }

	        response.sendRedirect(request.getContextPath()+"/login/Login.jsp");
	    }

}
