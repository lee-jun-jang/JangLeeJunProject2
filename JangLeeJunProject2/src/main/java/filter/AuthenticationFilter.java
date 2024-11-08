package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.JWTokens;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet Filter implementation class AuthenticationFilter
 * New->Filter로 생성
 * web.xml에 자동으로 <filter>태그 등록된다
 * 1.@WebFilter추가(web.xml에 등록된 <filter>태그 삭제)
 * 2.extends HttpFilter 삭제(클래스 생성시 슈퍼클래스를 지정안하면 됨)
 * 3.doFilter()만 남긴다
 * 
 * 
 * 회원제 게시판(bbs08)의 각 JSP페이지의 인증여부용 판단을 위한
 * IsUser.jsp 인클루드 처리한 거를 필터로 교체하자
 * 
 * 인증여부 판단 코드 추가후에는 각 JSP페이지의 IsUser.jsp 인클루드 처리한 거 주석처리
 * 
 */
@WebFilter()
public class AuthenticationFilter  implements Filter {

	@Override
	public void doFilter(jakarta.servlet.ServletRequest req, jakarta.servlet.ServletResponse resp,
			jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {
		//사전작업으로 로그인 여부 먼저 판단
		HttpServletRequest request =(HttpServletRequest)req;
		/*
		//세션기반 인증일때				
		Object authenticated=req.getSession().getAttribute("USERNAME");
		if(authenticated==null) {
			//방법1)브라우저로 HTML태그를 응답
			HttpServletResponse response=(HttpServletResponse)resp;
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('Please Use After Login...')");
			out.println("location.replace('"+request.getContextPath()+"/session06/Login.jsp')");
			out.println("</script>");
			return;
		}
		chain.doFilter(req, resp);
		*/
		//토큰기반 인증일때
		String token=JWTokens.getTokenInCookie(request, request.getServletContext().getInitParameter("TOKEN-NAME"));
		boolean isAuthenticated=JWTokens.verifyToken(token);
		if(!isAuthenticated) {
			//방법1)브라우저로 HTML태그를 응답
			/*
			HttpServletResponse response=(HttpServletResponse)resp;			
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('Please Use After Login...')");
			out.println("location.replace('"+request.getContextPath()+"/session06/LoginBasedToken.jsp')");
			out.println("</script>");
			return;*/
			//방법2)로그인 페이지로 포워드
			request.setAttribute("errorMsg", "Please Use After Login...");
			request.getRequestDispatcher("/l/Login.jsp").forward(req, resp);
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(req, resp);
		
	}

	

}
