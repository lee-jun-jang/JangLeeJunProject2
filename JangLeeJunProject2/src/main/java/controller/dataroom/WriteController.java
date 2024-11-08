package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDao;
import model.board.BoardDto;

@WebServlet("/Dataroom/Write.ict")
public class WriteController extends HttpServlet {
	
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/dataroom14/Write.jsp").forward(req, resp);
	}/////

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String username = req.getParameter("username");
	
		
	    System.out.println("title: " + title);
        System.out.println("content: " + content);
        System.out.println("username: " + username);
 
        
        BoardDto item = new BoardDto();
        item.setTitle(title);
        item.setContent(content);
        item.setUsername(username);
        
        
        BoardDao dao = new BoardDao(getServletContext());
        int affected = dao.insert(item);
        dao.close();

        System.out.println("affected:"+affected);
        

		if (affected == 1) {
			resp.sendRedirect(req.getContextPath()+"/Dataroom/List.ict");
		} else {
			req.getRequestDispatcher("/WEB-INF/dataroom14/Write.jsp").forward(req, resp);
		}
	}
}
