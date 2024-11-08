package controller.dataroom;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.member.MemberDao;
import model.member.MemberDto;

@WebServlet("/Dataroom/Register.ict")
public class RegisterController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		resp.sendRedirect(req.getContextPath()+"/login/Register.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String[] interArray = req.getParameterValues("inter");
        String inter = Arrays.toString(interArray);
		String grade = req.getParameter("grade");
		String info = req.getParameter("info");
		
		
		MemberDao mDao = new MemberDao(getServletContext());
		MemberDto mDto = new MemberDto();
		mDto.setUsername(username);
		mDto.setPassword(password);
		mDto.setName(name);
		mDto.setGender(gender);
		mDto.setInter(inter);
		mDto.setGrade(grade);
		mDto.setInfo(info);
		int joinResult = mDao.insert(mDto);
		mDao.close();
		
		if (joinResult == 1) {
			req.setAttribute("joinResult", joinResult);
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			resp.sendRedirect(req.getContextPath()+"/login/Login.jsp");
			
		} else {
			req.setAttribute("joinResult", 0);
			req.getRequestDispatcher("/login/Register.jsp").forward(req, resp);
		}
	}
}
