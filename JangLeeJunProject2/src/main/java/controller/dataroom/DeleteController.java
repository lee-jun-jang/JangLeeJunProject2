package controller.dataroom;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDao;
import model.board.BoardDto;

@WebServlet("/Dataroom/Delete.ict")
public class DeleteController  extends HttpServlet {
	 @Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   String idParam = req.getParameter("id");
       long id = Long.parseLong(idParam);

       BoardDao dao = new BoardDao(getServletContext());
       BoardDto dto = new BoardDto();
       dto.setId(id);

       int affected = dao.delete(dto);
       dao.close();
       resp.sendRedirect(req.getContextPath() + "/Dataroom/List.ict");

   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
   }
}

