package controller.dataroom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.JWTokens;
import model.member.MemberDao;

@WebServlet("/Dataroom/Login.ict")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/login/Login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

	        MemberDao dao = new MemberDao(getServletContext());
	        boolean isUser = dao.isUser(username, password);
	        dao.close();

	        HttpSession session = req.getSession();

	        if (isUser) {

	            Map<String, Object> payloads = new HashMap<>();
	            payloads.put("password", password); 

	            long expirationTime = 1000 * 60 * 60 * 3;
	            String token = JWTokens.createToken(username, payloads, expirationTime);

	            session.setAttribute("token", token);
	            session.setAttribute("username", username);

	            resp.sendRedirect(req.getContextPath() + "/Dataroom/List.ict");
	        } else {

	            req.setAttribute("errorMsg", "아이디/비번 불일치");

	            RequestDispatcher dispatcher = req.getRequestDispatcher("/login/Login.jsp");
	            dispatcher.forward(req, resp);
	        }
	    }
}