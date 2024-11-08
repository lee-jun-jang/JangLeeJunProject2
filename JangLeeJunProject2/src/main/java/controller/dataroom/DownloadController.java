package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FileUtils;
import model.board.BoardDao;


@WebServlet("/Dataroom/Download.ict")
public class DownloadController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String filename = req.getParameter("filename");

		FileUtils.download(filename, "/upload", req, resp);

		BoardDao dao = new BoardDao(getServletContext());
		dao.updateDownCount(id);
		dao.close();
	}
}
