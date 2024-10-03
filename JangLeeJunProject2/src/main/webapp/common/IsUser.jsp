<%@page import="model.JWTokens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- IsUser.jsp -->
<!-- 세션 기반 -->

	<%if(session.getAttribute("username")==null){%>
		<script>
			alert("로그인후 이용하세요");
			location.replace("<%=request.getContextPath() %>/login/Login.jsp");
		</script>
	<%  return; }%>

<%-- 
<!-- 토큰 기반 -->
<%if(!JWTokens.verifyToken(JWTokens.getTokenInCookie(request, application.getInitParameter("TOKEN-NAME")))){%>
	<script>
		alert("로그인후 이용하세요");
		location.replace("<%=request.getContextPath() %>/login/Login.jsp");
	</script>
<%  return; }%>
--%>
