package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/bbs08/*")
public class RuntimeFilter implements Filter {

	
	public RuntimeFilter() {
		System.out.println("RuntimeFilter의 생성자:Pre-loading");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("RuntimeFilter의 doFilter()");
		
		//chain.doFilter(req, resp);를 미 호출시 요청이 .JSP나 
		//요청을 처리하는 서블릿으로 전달 안된다(브라우저로 HTML이 출력이 안된다)
		//chain.doFilter(req, resp);//즉 이 코드를 반드시 작성해야 요청이 전달된다
		//[JSP나 서블릿의 요청처리 시간을 측정하는 작업을 필터로 구현]
		//사전 작업 코드(JSP나 요청처리용 서블릿에 요청 전달 전)
		System.out.println("[doFilter호출전 사전 작업]");
		HttpServletRequest request=(HttpServletRequest)req;
		String uri = request.getRequestURI();//사용자 요청 URI얻기
		long startTime = System.currentTimeMillis();//시작 시간 얻기
		chain.doFilter(req, resp);//JSP나 서블릿(혹은 다른 필터)으로 요청 전달
		//사후 작업 코드(JSP나 요청처리용 서블릿에 응답받는 후)
		System.out.println("[doFilter호출후 사후 작업]");
		long endTime = System.currentTimeMillis();//종료 시간 얻기
		System.out.println(String.format("요청 URI:%s,요청 처리시간:%s",uri,(endTime-startTime)/1000.0+"초"));
	}

	@Override
	public void destroy() {
		System.out.println("RuntimeFilter의 destroy()");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("RuntimeFilter의 init()");
	}
	
}
