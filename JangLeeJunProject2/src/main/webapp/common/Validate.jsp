<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Validate.jsp -->
<%!
	//서버단에서도 유효성 체크
	
	//텍스트박스류나 라디오박스에 적용하는 메소드
	private boolean isValidate(JspWriter out,String param,String message){
		if(param ==null || param.trim().length()==0){
			try{
				out.println("<script>");
				out.println("alert('"+message+"');");
				out.println("history.back();");
				out.println("</script>");
			}
			catch(IOException e){}
			return false;
		}
		return true;
	}/////
	//체크박스류에 적용하는 메소드
	private boolean isValidate(JspWriter out,String[] param,String message,int count){
		if(param ==null || param.length < count){
			try{
				out.println("<script>");
				out.println(String.format("alert('%s는 %s개 이상 선택하세요');",message,count));
				out.println("history.back();");
				out.println("</script>");
			}
			catch(IOException e){}
			return false;
		}
		return true;
	}

%>