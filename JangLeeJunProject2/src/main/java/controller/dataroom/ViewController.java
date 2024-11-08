package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDao;
import model.board.BoardDto;

@WebServlet("/Dataroom/View.ict")
public class ViewController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		BoardDao dao= new BoardDao(getServletContext());
		BoardDto dto= dao.findById(id);
		dao.close();

		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));

		req.setAttribute("record", dto);

		req.getRequestDispatcher("/WEB-INF/dataroom14/View.jsp").forward(req, resp);
	}
}
