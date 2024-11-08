package controller.dataroom;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.FileUtils;
import model.PagingUtil;
import model.board.BoardDao;
import model.board.BoardDto;



@WebServlet("/Dataroom/Edit.ict")
public class EditController extends HttpServlet {
	 @Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String idParam = req.getParameter("id");
		   String title = req.getParameter("title");
	        String content = req.getParameter("content");
	        String username = req.getParameter("username");
	        long id = Long.parseLong(idParam);

	        
	        BoardDto item = new BoardDto();
	        item.setContent(content);
	        item.setTitle(title);
	        item.setId(id);
	        item.setUsername(username);

	        BoardDao dao = new BoardDao(getServletContext());
	        int affected = dao.update(item);
	        dao.close();

	        resp.sendRedirect(req.getContextPath() + "/Dataroom/List.ict");
	      
	 }

	   @Override
	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  
		   String id = req.getParameter("id");
	        BoardDao dao = new BoardDao(getServletContext());
	        BoardDto record = dao.findById(id);
	        dao.close();

	        if (record != null) {
	            req.setAttribute("record", record);
	            req.getRequestDispatcher("/WEB-INF/dataroom14/Edit.jsp").forward(req, resp);
	        
	   }
	}
}
