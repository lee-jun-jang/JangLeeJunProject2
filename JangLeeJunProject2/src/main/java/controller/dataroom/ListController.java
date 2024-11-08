package controller.dataroom;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PagingUtil;
import model.board.BoardDao;
import model.board.BoardDto;

@WebServlet(urlPatterns = "/Dataroom/List.ict",initParams = {@WebInitParam(name = "PAGE-SIZE",value = "5"),@WebInitParam(name = "BLOCK-PAGE",value = "3")})
public class ListController extends HttpServlet {
	
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BoardDao dao = new BoardDao(getServletContext());
		Map<String,String> map = new HashMap<>();

		String pageSize= getInitParameter("PAGE-SIZE");
		String blockPage= getInitParameter("BLOCK-PAGE");
		map.put(PagingUtil.PAGE_SIZE,pageSize);
		map.put(PagingUtil.BLOCK_PAGE,blockPage);

		PagingUtil.setMapForPaging(map, dao, req);
		
	        List<BoardDto> records = dao.findAll(map);
	        dao.close();

		int totalRecordCount=Integer.parseInt(map.get(PagingUtil.TOTAL_RECORD_COUNT));
		int nowPage=Integer.parseInt(map.get(PagingUtil.NOWPAGE));
		
		String paging=PagingUtil.pagingBootStrapStyle(
				totalRecordCount,
				Integer.parseInt(pageSize),
				Integer.parseInt(blockPage),
				nowPage,
				req.getContextPath()+"/Dataroom/List.ict?");
		
		req.setAttribute("records", records);
		req.setAttribute("paging", paging);
		req.setAttribute("totalRecordCount", totalRecordCount);
        req.setAttribute("nowPage", nowPage);
        req.setAttribute("pageSize", pageSize);
        
	        req.getRequestDispatcher("/WEB-INF/dataroom14/List.jsp").forward(req, resp);
	}
}
